package com.dodo.common.framework.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.dodo.common.annotation.menu.DodoMenuLevel;
import com.dodo.common.annotation.report.ReportFieldType;
import com.dodo.common.annotation.report.ReportQueryType;
import com.dodo.common.database.hql.HqlHelper;
import com.dodo.common.database.hql.HqlHelper.MatchType;
import com.dodo.common.dynamicmodule.DynamicModuleDesignBean;
import com.dodo.common.framework.dao.HqlHelperDao;
import com.dodo.common.framework.service.DynamicModuleService;
import com.dodo.privilege.entity.admin_1.config_5.MenuInfo;
import com.dodo.privilege.entity.admin_1.config_5.Right;
import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleButton;
import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleEntity;
import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleField;
import com.dodo.privilege.enums.ModuleButtonEvent;
import com.dodo.privilege.enums.ModuleButtonModel;
import com.dodo.security.DodoSecurityMetadataSource;
import com.dodo.utils.CommonUtil;
import com.dodo.utils.config.DodoFrameworkConfigUtil;

/**
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
public class DynamicModuleServiceImpl implements DynamicModuleService {
    private HqlHelperDao helperDao;

    public HqlHelperDao getHelperDao() {
        return helperDao;
    }

    public void setHelperDao(HqlHelperDao helperDao) {
        this.helperDao = helperDao;
    }

    @Override
    public void saveOrUpdateDesign(DynamicModuleDesignBean designBean, HttpServletRequest request) {
        ModuleEntity moduleEntity = null;
        MenuInfo moduleEntityMenu = helperDao.load(
                DodoFrameworkConfigUtil.getRightTypeIdValue(designBean.getMenuSELECT()), MenuInfo.class);

        if (StringUtils.isNotBlank(designBean.getUpdateModuleEntityId())) {
            moduleEntity = helperDao.get(
                    DodoFrameworkConfigUtil.getRightTypeIdValue(designBean.getUpdateModuleEntityId()),
                    ModuleEntity.class);
            List<ModuleField> fields = moduleEntity.getModuleFields();
            moduleEntity.setModuleFields(null);
            for (ModuleField field : fields) {
                helperDao.delete(field);
            }
            List<ModuleButton> buttons = moduleEntity.getModuleButtons();
            moduleEntity.setModuleButtons(null);
            for (ModuleButton btn : buttons) {
                helperDao.delete(btn);
            }
        } else {
            moduleEntity = new ModuleEntity();
        }
        moduleEntity.setMenu(moduleEntityMenu);
        moduleEntity.setExecSql(designBean.getExecSql());
        moduleEntity.setName(CommonUtil.escapeHtml(designBean.getName()));
        if (StringUtils.isNotBlank(designBean.getUpdateModuleEntityId())) {
            helperDao.update(moduleEntity);
        } else {
            helperDao.save(moduleEntity);
        }

        // 插入当前菜单
        String menuLink = new StringBuilder().append("/dynamicmodule/view/").append(moduleEntity.getId())
                .append(".jhtml").toString();
        String rightExcelLink = new StringBuilder().append("/dynamicmodule/exportexcel/").append(moduleEntity.getId())
                .append(".jhtml").toString();
        HqlHelper helper = HqlHelper.queryFrom(MenuInfo.class).eq("menuLink", menuLink);
        MenuInfo menuInfo = helperDao.getEntity(helper);
        if (menuInfo == null) {
            menuInfo = new MenuInfo();
            menuInfo.setMenuLevel(DodoMenuLevel.LEVEL3);
            menuInfo.setMenuName(moduleEntity.getName());
            menuInfo.setMenuLink(menuLink);
            menuInfo.setParentMenuInfo(moduleEntityMenu);
            menuInfo.setMenuCode(getNextMenuCodeCode(DodoMenuLevel.LEVEL3));
            helperDao.save(menuInfo);
        } else {
            if (!menuInfo.getParentMenuInfo().equals(moduleEntityMenu)) {
                menuInfo.setParentMenuInfo(moduleEntityMenu);
            }
            menuInfo.setMenuName(moduleEntity.getName());
            helperDao.update(menuInfo);
        }
        // 将当前菜单的权限打上临时备注
        helper.resetQueryFrom(Right.class).update("rightRemark", "Temp").eq("menuInfo", menuInfo);
        helperDao.update(helper);

        // 权限处理 - VIEW
        helper.resetQueryFrom(Right.class).eq("rightLink", menuLink).eq("menuInfo", menuInfo);
        Right right = helperDao.getEntity(helper);
        if (right == null) {
            right = new Right();
            right.setRightLink(menuLink);
            right.setRightName(moduleEntity.getName() + "_VIEW");
            right.setMenuInfo(menuInfo);
            right.setRightCode(getNextRightCodeCode());
            helperDao.save(right);
        } else {
            right.setRightName(moduleEntity.getName() + "_VIEW");
            right.setRightRemark(null);// 必须
            helperDao.update(right);
        }

        // 权限处理 - Excel
        helper.resetQueryFrom(Right.class).eq("rightLink", rightExcelLink).eq("menuInfo", menuInfo);
        right = helperDao.getEntity(helper);
        if (right == null) {
            right = new Right();
            right.setRightLink(rightExcelLink);
            right.setRightName(moduleEntity.getName() + "_Excel");
            right.setMenuInfo(menuInfo);
            right.setRightCode(getNextRightCodeCode());
            helperDao.save(right);
        } else {
            right.setRightName(moduleEntity.getName() + "_Excel");
            right.setRightRemark(null);// 必须
            helperDao.update(right);
        }

        // 按钮处理
        String btnUrl = null;
        String btnUrlRight = null;
        for (int i = 0; i < designBean.getBtnName().size(); i++) {
            if (StringUtils.isBlank(designBean.getBtnLabel().get(i))) {
                continue;
            }
            ModuleButton moduleButton = new ModuleButton();
            moduleButton.setModuleEntity(moduleEntity);
            moduleButton.setBtnName(CommonUtil.escapeHtml(designBean.getBtnName().get(i)));
            moduleButton.setBtnLabel(CommonUtil.escapeHtml(designBean.getBtnLabel().get(i)));
            moduleButton.setShowCond(designBean.getShowCond().get(i).trim());
            moduleButton.setBtnModel(ModuleButtonModel.valueOf(designBean.getBtnModel().get(i)));
            moduleButton.setBtnEvent(ModuleButtonEvent.valueOf(designBean.getBtnEvent().get(i)));
            moduleButton.setAjaxTip(CommonUtil.escapeHtml(designBean.getAjaxTip().get(i)));
            moduleButton.setParamValueField(CommonUtil.escapeHtml(designBean.getParamValueField().get(i)));
            moduleButton.setParamName(CommonUtil.escapeHtml(designBean.getParamName().get(i)));
            moduleButton.setSortSeq(i);
            btnUrl = designBean.getBtnUrl().get(i).trim();
            moduleButton.setBtnUrl(btnUrl);
            // 后台菜单
            //            if (btnUrl.startsWith("{rootPath}")) {
            //                btnUrlRight = StringUtils.substringBeforeLast(btnUrl, "?");
            //                btnUrlRight = btnUrlRight.replace("{rootPath}", "");
            //
            //                // 获得一个权限
            //                helper.resetQueryFrom(Right.class).eq("rightLink", btnUrlRight);
            //                Right rightField = helperDao.getEntity(helper);
            //                if (rightField == null) {
            //                    rightField = new Right();
            //                    rightField.setRightLink(btnUrlRight);
            //                    rightField.setRightName(moduleEntity.getName() + "_BUTTON_" + moduleButton.getBtnLabel());
            //                    rightField.setRightCode(getNextRightCodeCode());
            //                    rightField.setMenuInfo(menuInfo);
            //                    helperDao.save(rightField);
            //                } else if (rightField.getMenuInfo() == null) {
            //                    rightField.setRightName(moduleEntity.getName() + "_BUTTON_" + moduleButton.getBtnLabel());
            //                    rightField.setMenuInfo(menuInfo);
            //                    rightField.setRightRemark(null);// 必须
            //                    helperDao.update(rightField);
            //                } else if (((Object) rightField.getMenuInfo().getId()).equals((Object) menuInfo.getId())) {
            //                    rightField.setRightRemark(null);// 必须
            //                    helperDao.update(rightField);
            //                }
            //                // 链接已经在别的菜单下存在
            //                else {
            //                    throw new DynamicModuleException(SpringUtil.getMessageBack("dodo.dynmodule.duplicate.jumplink",
            //                            new Object[] { btnUrl }, request));
            //                }
            //            }
            helperDao.save(moduleButton);
        }

        // 字段保存 及字段权限处理
        String jumpLink = null;
        String jumpLinkRight = null;
        for (int i = 0; i < designBean.getFieldLabel().size(); i++) {
            ModuleField moduleField = new ModuleField();
            moduleField.setFieldType(ReportFieldType.valueOf(designBean.getFieldType().get(i)));
            moduleField.setQueryField(CommonUtil.escapeHtml(designBean.getFieldName().get(i)));
            if (StringUtils.isNotBlank(designBean.getFieldQueryType().get(i))) {
                moduleField.setQueryType(ReportQueryType.valueOf(designBean.getFieldQueryType().get(i)));
            }
            moduleField.setModuleEntity(moduleEntity);
            moduleField.setShowName(CommonUtil.escapeHtml(designBean.getFieldLabel().get(i)));
            moduleField.setIsShow(designBean.getFieldIsShow().get(i));
            moduleField.setSortSeq(i);

            jumpLink = designBean.getJumpLink().get(i);
            if (StringUtils.isNotBlank(jumpLink)) {
                jumpLink = jumpLink.trim();
                moduleField.setJumpLink(jumpLink);
                // 后台菜单
                //                if (jumpLink.startsWith("{rootPath}")) {
                //                    jumpLinkRight = StringUtils.substringBeforeLast(jumpLink, "?");
                //                    jumpLinkRight = jumpLinkRight.replace("{rootPath}", "");
                //
                //                    // 获得一个权限
                //                    helper.resetQueryFrom(Right.class).eq("rightLink", jumpLinkRight);
                //                    Right rightField = helperDao.getEntity(helper);
                //                    if (rightField == null) {
                //                        rightField = new Right();
                //                        rightField.setRightLink(jumpLinkRight);
                //                        rightField.setRightName(moduleEntity.getName()
                //                                + "_DETAIL_"
                //                                + (StringUtils.isBlank(moduleField.getShowName()) ? CommonUtil.escapeHtml(moduleField
                //                                        .getQueryField()) : moduleField.getShowName()));
                //                        rightField.setRightCode(getNextRightCodeCode());
                //                        rightField.setMenuInfo(menuInfo);
                //                        helperDao.save(rightField);
                //                    } else if (rightField.getMenuInfo() == null) {
                //                        rightField.setRightName(moduleEntity.getName()
                //                                + "_DETAIL_"
                //                                + (StringUtils.isBlank(moduleField.getShowName()) ? CommonUtil.escapeHtml(moduleField
                //                                        .getQueryField()) : moduleField.getShowName()));
                //                        rightField.setMenuInfo(menuInfo);
                //                        rightField.setRightRemark(null);// 必须
                //                        helperDao.update(rightField);
                //                    } else if (((Object) rightField.getMenuInfo().getId()).equals((Object) menuInfo.getId())) {
                //                        rightField.setRightRemark(null);// 必须
                //                        helperDao.update(rightField);
                //                    }
                //                    // 链接已经在别的菜单下存在
                //                    else {
                //                        throw new DynamicModuleException(SpringUtil.getMessageBack("dodo.dynmodule.duplicate.jumplink",
                //                                new Object[] { jumpLink }, request));
                //                    }
                //                }
            }
            helperDao.save(moduleField);
        }

        // 更新失效权限
        helper.resetQueryFrom(Right.class).update("menuInfo", null).eq("rightRemark", "Temp").eq("menuInfo", menuInfo);
        helperDao.update(helper);

        DodoSecurityMetadataSource.refreshSysMetadata = Boolean.TRUE;
    }

    private String getNextRightCodeCode() {
        HqlHelper helper = HqlHelper.queryFrom(Right.class);
        helper.max("rightCode", "rightCode").like("rightCode", "9", MatchType.START);
        String maxValue = helperDao.getRecordGroup(helper).get("rightCode");
        if (maxValue == null) {
            return "900001";
        } else {
            return new java.math.BigDecimal(maxValue).add(new java.math.BigDecimal("1")).toString();
        }
    }

    private String getNextMenuCodeCode(DodoMenuLevel menuLevel) {
        HqlHelper helper = HqlHelper.queryFrom(MenuInfo.class);
        helper.max("menuCode", "menuCode");
        if (menuLevel == DodoMenuLevel.LEVEL1) {
            helper.like("menuCode", "6", MatchType.START);
        } else if (menuLevel == DodoMenuLevel.LEVEL2) {
            helper.like("menuCode", "7", MatchType.START);
        } else if (menuLevel == DodoMenuLevel.LEVEL3) {
            helper.like("menuCode", "8", MatchType.START);
        }

        String maxValue = helperDao.getRecordGroup(helper).get("menuCode");

        if (maxValue == null) {
            if (menuLevel == DodoMenuLevel.LEVEL1) {
                return "600001";
            } else if (menuLevel == DodoMenuLevel.LEVEL2) {
                return "700001";
            } else if (menuLevel == DodoMenuLevel.LEVEL3) {
                return "800001";
            }
            return null;
        } else {
            return new java.math.BigDecimal(maxValue).add(new java.math.BigDecimal("1")).toString();
        }
    }
}
