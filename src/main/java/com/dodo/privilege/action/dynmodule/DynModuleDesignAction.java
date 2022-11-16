package com.dodo.privilege.action.dynmodule;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.common.annotation.menu.DodoMenuLevel;
import com.dodo.common.annotation.report.ReportFieldType;
import com.dodo.common.annotation.report.ReportQueryType;
import com.dodo.common.database.data.Record;
import com.dodo.common.database.hql.HqlHelper;
import com.dodo.common.dynamicmodule.DynamicModuleDesignBean;
import com.dodo.common.dynamicmodule.DynamicModuleQueryResult;
import com.dodo.common.dynamicmodule.DynamicModuleQueryUtil;
import com.dodo.common.dynamicmodule.DynamicModuleResultSetExtractor;
import com.dodo.common.dynamicmodule.EasyuiResultBean;
import com.dodo.common.framework.service.DynamicModuleService;
import com.dodo.common.framework.service.HqlHelperService;
import com.dodo.common.framework.service.JdbcService;
import com.dodo.common.sqlreport.SqlReportLimit;
import com.dodo.privilege.entity.admin_1.base_1.Admin;
import com.dodo.privilege.entity.admin_1.config_5.FormModel;
import com.dodo.privilege.entity.admin_1.config_5.MenuInfo;
import com.dodo.privilege.entity.admin_1.config_5.Right;
import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleButton;
import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleEntity;
import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleField;
import com.dodo.privilege.security.DodoSecurityService;
import com.dodo.utils.CommonUtil;
import com.dodo.utils.ExcelUtils;
import com.dodo.utils.SpringUtil;
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
@Controller
@RequestMapping("${dodo.backmanage.view.rootPath}/dynamicmodule")
public class DynModuleDesignAction {

    private static Pattern       patternNumber     = Pattern.compile("(\\d+)");

    private static Pattern       patternValueIndex = Pattern.compile("[a-z_A-Z\\d]{3,}");

    @Autowired
    private JdbcService          jdbcService;

    @Autowired
    private HqlHelperService     hqlHelperService;

    @Autowired
    private DynamicModuleService dynamicModuleService;

    @Autowired
    private DodoSecurityService  dodoSecurityService;

    /**
     * 动态模块设计 - 开始
     * **/
    @RequestMapping("/design.jhtml")
    public String design(Model model, String entityIds, String entityId) {
        HqlHelper helper = HqlHelper.queryFrom(MenuInfo.class).ne("menuLevel", DodoMenuLevel.LEVEL3);
        helper.fetch("menuCode", "menuLevel", "menuName", "id", "menuNameKey");
        model.addAttribute("menus", hqlHelperService.getRecords(helper, Boolean.FALSE).getRawData());

        String updateModuleEntityId = null;
        if (StringUtils.isNotBlank(entityId)) {
            updateModuleEntityId = entityId;
        } else if (StringUtils.isNotBlank(entityIds)) {
            Matcher matcher = patternNumber.matcher(entityIds);
            if (matcher.find()) {
                updateModuleEntityId = matcher.group(1);
            }
        }
        ModuleEntity moduleEntity = null;
        if (updateModuleEntityId != null) {
            moduleEntity = hqlHelperService.get(DodoFrameworkConfigUtil.getRightTypeIdValue(updateModuleEntityId),
                    ModuleEntity.class);
        }
        if (moduleEntity != null) {
            model.addAttribute("updateModuleEntityId", updateModuleEntityId);
            model.addAttribute("moduleEntity", moduleEntity);
        }
        return "dynmoduledesign";
    }

    /**
     * SQL报表设计器 高亮显示表名 并悬浮提示使用
     * 
     */
    @RequestMapping("/dbmeta.jhtml")
    @ResponseBody
    public List<DBMeta> dbMetadata() {
        List<DBMeta> metadata = new ArrayList<DBMeta>();
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcService.getConnection();
            DatabaseMetaData dbMetaData = connection.getMetaData();
            resultSet = dbMetaData.getTables(connection.getCatalog(), null, "%", new String[] { "TABLE", "VIEW" });
            DBMeta dbMeta = null;
            ResultSet rs = null;
            while (resultSet.next()) {
                dbMeta = new DBMeta(resultSet.getString("TABLE_NAME"), "table");
                metadata.add(dbMeta);
                try {
                    rs = dbMetaData.getColumns(connection.getCatalog(), null, dbMeta.getName(), "%");
                    while (rs.next()) {
                        dbMeta = new DBMeta(rs.getString("COLUMN_NAME"), "column");
                        if (StringUtils.isNotBlank(dbMeta.getName())) {
                            metadata.add(dbMeta);
                        }
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return metadata;
    }

    /**
     * 查询类型
     */
    @RequestMapping("/queryType.jhtml")
    @ResponseBody
    public Map<String, String> queryType(HttpServletRequest request) {
        ReportQueryType[] reportQueryTypes = ReportQueryType.values();
        Map<String, String> qMap = new HashMap<String, String>(reportQueryTypes.length);
        for (ReportQueryType queryType : reportQueryTypes) {
            qMap.put(queryType.name(), SpringUtil.getMessageBack(queryType.getNameKey(), request));
        }
        return qMap;
    }

    /**
     * 字段类型
     */
    @RequestMapping("/fieldType.jhtml")
    @ResponseBody
    public Map<String, String> fieldType(HttpServletRequest request) {
        ReportFieldType[] reportFieldTypes = ReportFieldType.values();
        Map<String, String> qMap = new HashMap<String, String>(reportFieldTypes.length);
        for (ReportFieldType fieldType : reportFieldTypes) {
            qMap.put(fieldType.name(), SpringUtil.getMessageBack(fieldType.getNameKey(), request));
        }
        return qMap;
    }

    /**
     * 查询数据 - 分页
     * **/
    @RequestMapping("/query.jhtml")
    @ResponseBody
    public Object getQueryResult(String execSQL, Integer pageNumber, Integer pageSize) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Long currTime = System.currentTimeMillis();
        try {
            SqlReportLimit dynamicModuleLimit = hqlHelperService.getLimitSQL(execSQL, null, null, pageNumber, pageSize);
            DynamicModuleQueryResult dynamicModuleQueryResult = null;
            if (dynamicModuleLimit.getPreparedStatementSetter() == null) {
                dynamicModuleQueryResult = jdbcService.query(dynamicModuleLimit.getLimitSql(),
                        new DynamicModuleResultSetExtractor());
            } else {
                dynamicModuleQueryResult = jdbcService.query(dynamicModuleLimit.getLimitSql(),
                        dynamicModuleLimit.getPreparedStatementSetter(), new DynamicModuleResultSetExtractor());
            }
            dynamicModuleQueryResult.setPageNumber(dynamicModuleLimit.getPageNumber());
            dynamicModuleQueryResult.setPageSize(dynamicModuleLimit.getPageSize());
            dynamicModuleQueryResult
                    .setTotalCount(jdbcService.queryForInt(hqlHelperService.getCountSQL(execSQL, null)));
            dynamicModuleQueryResult.setTimeMills(System.currentTimeMillis() - currTime);
            return dynamicModuleQueryResult;
        } catch (Exception e) {
            e.printStackTrace();
            DynamicModuleQueryResult dynamicModuleQueryResult = new DynamicModuleQueryResult();
            dynamicModuleQueryResult.setIsSuccess(Boolean.FALSE);
            dynamicModuleQueryResult.setMessage(e.getMessage());
            dynamicModuleQueryResult.setTimeMills(System.currentTimeMillis() - currTime);
            return dynamicModuleQueryResult;
        }
    }

    /**
     * 查询数据 - 分页 - easyui
     * **/
    @RequestMapping("/easyui/query.jhtml")
    @ResponseBody
    public Object getQueryResultEasyui(String execSQL, Integer page, Integer rows) {
        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 20;
        }
        try {
            SqlReportLimit dynamicModuleLimit = hqlHelperService.getLimitSQL(execSQL, null, null, page, rows);
            DynamicModuleQueryResult dynamicModuleQueryResult = null;
            if (dynamicModuleLimit.getPreparedStatementSetter() == null) {
                dynamicModuleQueryResult = jdbcService.query(dynamicModuleLimit.getLimitSql(),
                        new DynamicModuleResultSetExtractor());
            } else {
                dynamicModuleQueryResult = jdbcService.query(dynamicModuleLimit.getLimitSql(),
                        dynamicModuleLimit.getPreparedStatementSetter(), new DynamicModuleResultSetExtractor());
            }

            EasyuiResultBean easyuiResultBean = new EasyuiResultBean();
            easyuiResultBean.setTotal(jdbcService.queryForInt(hqlHelperService.getCountSQL(execSQL, null)));

            for (List<String> list : dynamicModuleQueryResult.getQueryDatas()) {
                Map<String, String> map = new HashMap<String, String>(list.size());
                for (int i = 0; i < list.size(); i++) {
                    map.put(i + "", list.get(i));
                }
                easyuiResultBean.addRow(map);
            }

            return easyuiResultBean;
        } catch (Exception e) {
            e.printStackTrace();
            DynamicModuleQueryResult dynamicModuleQueryResult = new DynamicModuleQueryResult();
            dynamicModuleQueryResult.setIsSuccess(Boolean.FALSE);
            dynamicModuleQueryResult.setMessage(e.getMessage());
            return dynamicModuleQueryResult;
        }
    }

    /**
     * 保存设计方案
     * **/
    @RequestMapping("/saveOrUpdate.jhtml")
    @ResponseBody
    public Map<String, Object> addOneReport(DynamicModuleDesignBean designBean, HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<String, Object>(4);
        returnMap.put("isSuccess", Boolean.FALSE);
        returnMap.put("message", SpringUtil.getMessageBack("dodo.dynmodule.design.submit.fail", request));
        try {

            List<String> valueIndexList = designBean.getValueIndex();
            Set<String> valueIndexSet = new HashSet<String>(valueIndexList);
            // 不允许重复
            if (valueIndexSet.size() != valueIndexList.size()) {
                returnMap.put("message", SpringUtil.getMessageBack(
                        "dodo.privilege.dynmodule.config.infoTip.valueIndex.duplicate", request));
                return returnMap;
            }

            for (String valueIndex : valueIndexList) {
                // 不能为空
                if (StringUtils.isBlank(valueIndex)) {
                    returnMap.put("message", SpringUtil.getMessageBack("dodo.infotip.notnull",
                            new Object[] { SpringUtil.getMessageBack(
                                    "dodo.privilege.dynmodule.config.ModuleField.namekey.valueIndex", request) },
                            request));
                    return returnMap;
                }
                // 满足要求
                if (!patternValueIndex.matcher(valueIndex).find()) {
                    returnMap.put("message", SpringUtil.getMessageBack(
                            "dodo.privilege.dynmodule.config.infoTip.valueIndex.reg", request));
                    return returnMap;
                }
                // 首字母不是数字
                if (patternNumber.matcher(StringUtils.substring(valueIndex, 0, 1)).find()) {
                    returnMap.put("message", SpringUtil.getMessageBack(
                            "dodo.privilege.dynmodule.config.infoTip.valueIndex.firstLetter", request));
                    return returnMap;
                }
            }

            HqlHelper helper = HqlHelper.queryFrom(FormModel.class).fetch("id");
            for (String formModelId : designBean.getFormModel()) {
                if (StringUtils.isBlank(formModelId)) {
                    continue;
                }
                helper.resetQueryParameters().eq("id", formModelId);
                if (hqlHelperService.getRecord(helper) == null) {
                    String formModelEntityName = SpringUtil.getMessageBack(
                            "dodo.privilege.admin.config.FormModel.entityKey", request);
                    returnMap.put("isSuccess", Boolean.FALSE);
                    returnMap.put(
                            "message",
                            SpringUtil.getMessageBack("dodo.infotip.entity.notfound", new Object[] { formModelId,
                                    formModelEntityName }, request));
                    return returnMap;
                }
            }

            dynamicModuleService.saveOrUpdateDesign(designBean, request);
            dodoSecurityService.refreshCurrLoginAdmin();
            returnMap.put("isSuccess", Boolean.TRUE);
            returnMap.put("message", SpringUtil.getMessageBack("dodo.dynmodule.design.submit.success", request));
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("message", e.getMessage());
        }
        return returnMap;
    }

    /**
     * 动态模块查看 - 没有配置权限入口
     * **/
    @RequestMapping("/view.jhtml")
    public String viewerRedirect(Model model, String entityId, Integer pageNumber, Integer pageSize, String queryFlag,
            Integer page, Integer rows, String selfView, HttpServletRequest request) {
        return viewer(model, entityId, pageNumber, pageSize, queryFlag, page, rows,
                StringUtils.isBlank(selfView) ? "dynmoduleviewer_design" : selfView, request);
    }

    /**
     * 动态模块查看 - 开始
     * **/
    @RequestMapping("/view/{moduleId}.jhtml")
    public String viewer(Model model, @PathVariable String moduleId, Integer pageNumber, Integer pageSize,
            String queryFlag, Integer page, Integer rows, String selfView, HttpServletRequest request) {
        // for easyui
        if (page != null) {
            pageNumber = page;
        }
        // for easyui
        if (rows != null) {
            pageSize = rows;
        }

        if (pageNumber == null) {
            pageNumber = 1;
        }

        if (pageSize == null) {
            pageSize = 20;
        }
        try {
            Admin admin = (Admin) dodoSecurityService.getLoginPrincipal();
            model.addAttribute("admin", admin);

            ModuleEntity moduleEntity = hqlHelperService.get(DodoFrameworkConfigUtil.getRightTypeIdValue(moduleId),
                    ModuleEntity.class);
            model.addAttribute("moduleEntity", moduleEntity);

            // 初始化按钮权限
            String menuLink = new StringBuilder().append("/dynamicmodule/view/").append(moduleEntity.getId())
                    .append(".jhtml").toString();
            HqlHelper helper = HqlHelper.queryFrom(MenuInfo.class).eq("menuLink", menuLink);
            MenuInfo menuInfo = hqlHelperService.getEntity(helper);

            // 查询按钮权限
            helper = helper.resetQueryFrom(Right.class).fetch("rightCode");
            Map<String, String> btnCodes = new HashMap<String, String>();
            List<ModuleButton> moduleButtons = moduleEntity.getModuleButtons();
            if (moduleButtons != null) {
                for (ModuleButton btn : moduleButtons) {
                    helper.resetQueryParameters().eq("rightName", btn.getBtnLabel()).eq("menuInfo", menuInfo);
                    Record btnRight = hqlHelperService.getRecord(helper);
                    if (btnRight != null) {
                        String rightCode = btnRight.get("rightCode");
                        btnCodes.put(btn.getBtnName(), rightCode);
                    }
                }
            }
            model.addAttribute("btnCodes", btnCodes);

            model.addAttribute("queryFlag", queryFlag);
            Map<String, Object> parsedQueryFields = DynamicModuleQueryUtil.parseQueryFields(
                    moduleEntity.getModuleFields(), request);
            model.addAttribute("parsedQueryFields", parsedQueryFields);
            List<Object> whereClauseVars = new ArrayList<Object>();
            String whereClause = DynamicModuleQueryUtil.getWhereClause(moduleEntity.getModuleFields(),
                    parsedQueryFields, whereClauseVars);

            if (!"0".equals(queryFlag)) {

                SqlReportLimit dynamicModuleLimit = hqlHelperService.getLimitSQL(moduleEntity.getExecSql(),
                        whereClause, whereClauseVars, pageNumber, pageSize);
                DynamicModuleQueryResult dynamicModuleQueryResult = null;
                if (dynamicModuleLimit.getPreparedStatementSetter() == null) {
                    dynamicModuleQueryResult = jdbcService.query(dynamicModuleLimit.getLimitSql(),
                            new DynamicModuleResultSetExtractor());
                } else {
                    dynamicModuleQueryResult = jdbcService.query(dynamicModuleLimit.getLimitSql(),
                            dynamicModuleLimit.getPreparedStatementSetter(), new DynamicModuleResultSetExtractor());
                }
                dynamicModuleQueryResult.setPageNumber(dynamicModuleLimit.getPageNumber());
                dynamicModuleQueryResult.setPageSize(dynamicModuleLimit.getPageSize());
                if (whereClauseVars.size() == 0) {
                    dynamicModuleQueryResult.setTotalCount(jdbcService.queryForInt(hqlHelperService.getCountSQL(
                            moduleEntity.getExecSql(), whereClause)));
                } else {
                    dynamicModuleQueryResult.setTotalCount(jdbcService.queryForInt(
                            hqlHelperService.getCountSQL(moduleEntity.getExecSql(), whereClause),
                            whereClauseVars.toArray(new Object[whereClauseVars.size()])));
                }
                model.addAttribute("dynamicModuleQueryResult", dynamicModuleQueryResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("dialogType", "error");
            model.addAttribute("infoTip", SpringUtil.getMessageBack("dodo.dynmodule.view.exception", request));
            return "info";
        }
        return selfView == null ? "dynmoduleviewer" : selfView;
    }

    /**
     * 动态模块导出- 开始
     * **/
    @RequestMapping("/exportexcel/{moduleId}.jhtml")
    public void exportExcel(Model model, @PathVariable String moduleId, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            ModuleEntity moduleEntity = hqlHelperService.get(DodoFrameworkConfigUtil.getRightTypeIdValue(moduleId),
                    ModuleEntity.class);
            Workbook workbook = WorkbookFactory.create(true);
            String exportFileName = CommonUtil.getDownloadFilename(
                    request,
                    moduleEntity.getName()
                            + "_"
                            + CommonUtil.getSpecialDateStr(new java.sql.Date(System.currentTimeMillis()),
                                    "yyyy-MM-dd_HH-mm-ss"))
                    + ".xlsx";
            List<String> fileTitle = new LinkedList<String>();
            List<ModuleField> moduleFields = moduleEntity.getModuleFields();
            List<Integer> hiddenFields = new ArrayList<Integer>(moduleFields.size());
            ModuleField field = null;
            for (int i = 0; i < moduleFields.size(); i++) {
                field = moduleFields.get(i);
                if (field.getIsShow()) {
                    fileTitle.add(StringUtils.isBlank(field.getShowName()) ? field.getQueryField() : field
                            .getShowName());
                } else {
                    hiddenFields.add(i);
                }
            }

            Map<String, Object> parsedQueryFields = DynamicModuleQueryUtil.parseQueryFields(
                    moduleEntity.getModuleFields(), request);
            List<Object> whereClauseVars = new ArrayList<Object>();
            String whereClause = DynamicModuleQueryUtil.getWhereClause(moduleEntity.getModuleFields(),
                    parsedQueryFields, whereClauseVars);

            SqlReportLimit dynamicModuleLimit = null;
            DynamicModuleQueryResult dynamicModuleQueryResult = null;

            int excelFetchRow = 100;
            int sheetMaxRow = 100 * 500;
            int beginRow = 2;
            int sheetIndex = -1;
            List<List<String>> queryDatas = null;
            for (int i = 1;; i++) {
                dynamicModuleLimit = hqlHelperService.getLimitSQL(moduleEntity.getExecSql(), whereClause,
                        whereClauseVars, i, excelFetchRow);
                if (dynamicModuleLimit.getPreparedStatementSetter() == null) {
                    dynamicModuleQueryResult = jdbcService.query(dynamicModuleLimit.getLimitSql(),
                            new DynamicModuleResultSetExtractor());
                } else {
                    dynamicModuleQueryResult = jdbcService.query(dynamicModuleLimit.getLimitSql(),
                            dynamicModuleLimit.getPreparedStatementSetter(), new DynamicModuleResultSetExtractor());
                }
                queryDatas = dynamicModuleQueryResult.getQueryDatas();
                if (queryDatas == null || queryDatas.size() == 0) {
                    break;
                }

                if (hiddenFields.size() > 0) {
                    for (List<String> queryData : queryDatas) {
                        for (int j = hiddenFields.size() - 1; j >= 0; j--) {
                            queryData.remove(hiddenFields.get(j).intValue());
                        }
                    }
                }

                if (((i - 1) * excelFetchRow) % sheetMaxRow == 0) {
                    ++sheetIndex;
                    beginRow = 2;
                    workbook = ExcelUtils.writeWorkbookTitle(workbook, fileTitle, null);
                }
                workbook = ExcelUtils.writeWorkbookContent(workbook, sheetIndex, queryDatas, beginRow);
                beginRow = beginRow + queryDatas.size();
            }
            if (sheetIndex == -1) {
                workbook = ExcelUtils.writeWorkbookTitle(workbook, fileTitle, null);
            }
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + exportFileName + "\"");
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DBMeta {
    private String name;
    private String type;

    public DBMeta(String name, String type) {
        super();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}