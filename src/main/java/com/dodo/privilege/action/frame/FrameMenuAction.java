package com.dodo.privilege.action.frame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.common.captcha.octo.CaptchaUtil;
import com.dodo.common.database.data.Record;
import com.dodo.common.database.data.Records;
import com.dodo.common.database.hql.HqlHelper;
import com.dodo.common.framework.bean.pager.PageModel.OrderType;
import com.dodo.common.framework.bean.tree.DodoTree;
import com.dodo.common.framework.bean.tree.DodoTreeNode;
import com.dodo.common.framework.service.HqlHelperService;
import com.dodo.privilege.entity.admin_1.base_1.Admin;
import com.dodo.privilege.entity.admin_1.config_5.Entity;
import com.dodo.privilege.entity.admin_1.data_4.AdvancedQueryPlan;
import com.dodo.privilege.security.DodoSecurityService;
import com.dodo.utils.CommonUtil;
import com.dodo.utils.JacksonUtil;
import com.dodo.utils.RespData;
import com.dodo.utils.SpringUtil;
import com.dodo.utils.config.DodoFrameworkConfigUtil.DodoCommonConfigUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

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
@RequestMapping("${dodo.backmanage.view.rootPath}/framemenu")
public class FrameMenuAction {
    @Autowired
    private DodoSecurityService securityService;

    @Autowired
    private HqlHelperService    helperService;

    private final String        CHANGE_PWD_SALT_IN_SESSION = "CHANGE_PWD_SALT_IN_SESSION";

    @RequestMapping("/changepwd.jhtml")
    public String changepwd(Model model, HttpServletRequest request) {
        String changePwdSalt = UUID.randomUUID().toString();
        HttpSession session = request.getSession();
        session.setAttribute(CHANGE_PWD_SALT_IN_SESSION, changePwdSalt);
        model.addAttribute("changePwdSalt", changePwdSalt);
        model.addAttribute("passwordSalt", DodoCommonConfigUtil.passwordSalt);

        Admin admin = (Admin) securityService.getLoginPrincipal();
        model.addAttribute("userName", admin.getUsername());
        model.addAttribute("realName", admin.getName());
        return "page_changepwd";
    }

    @RequestMapping("/add_query_plan.jhtml")
    @ResponseBody
    public RespData addQueryPlan(Model model, HttpServletRequest request, String entityClassFullName, String planTitle) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Enumeration<String> e = request.getParameterNames();
        if (e.hasMoreElements()) {
            while (e.hasMoreElements()) {
                String paramName = e.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                paramName = paramName.replace("[]", "");
                if (paramValues.length == 1) {
                    dataMap.put(paramName, paramValues[0]);
                } else {
                    List<Object> tempList = new ArrayList<Object>();
                    for (int i = 0; i < paramValues.length; i++) {
                        tempList.add(paramValues[i]);
                    }
                    dataMap.put(paramName, tempList);
                }
            }
        }

        String queryPlanName = SpringUtil.getMessageBack("dodo.privilege.admin.data.AdvancedQueryPlan.entityKey",
                new Object[0], request);

        // addBy
        Admin admin = (Admin) securityService.getLoginPrincipal();

        // entity
        HqlHelper queryEntityHelper = HqlHelper.queryFrom(Entity.class);
        queryEntityHelper.eq("className", entityClassFullName);
        Entity entity = helperService.getEntity(queryEntityHelper);

        // planDetail
        String planDetail = "";
        try {
            planDetail = JacksonUtil.toJackson(dataMap, Boolean.TRUE);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }

        if (StringUtils.isBlank(planDetail)) {
            return RespData.fail(SpringUtil.getMessageBack("dodo.infotip.add.fail", new Object[] { queryPlanName },
                    request));
        }

        // Save
        AdvancedQueryPlan plan = new AdvancedQueryPlan();
        plan.setAddBy(admin);
        plan.setEntity(entity);
        plan.setPlanDetail(planDetail);
        plan.setPlanTitle(CommonUtil.escapeHtml(planTitle));
        helperService.save(plan);

        return RespData.success(
                SpringUtil.getMessageBack("dodo.infotip.add.success", new Object[] { queryPlanName }, request), null);
    }

    @RequestMapping("/delete_query_plan.jhtml")
    @ResponseBody
    public RespData deleteQueryPlan(Model model, HttpServletRequest request, String queryPlanId) {
        HqlHelper helper = HqlHelper.queryFrom(AdvancedQueryPlan.class);
        helper.eq("id", queryPlanId);
        helperService.delete(helper);

        String queryPlanName = SpringUtil.getMessageBack("dodo.privilege.admin.data.AdvancedQueryPlan.entityKey",
                new Object[0], request);

        return RespData
                .success(SpringUtil.getMessageBack("dodo.infotip.delete.success", new Object[] { queryPlanName },
                        request), null);
    }

    @RequestMapping("/get_all_query_plan.jhtml")
    @ResponseBody
    public Map<String, Object> getAllQueryPlan(Model model, HttpServletRequest request, String entityClassFullName) {
        HqlHelper helper = HqlHelper.queryFrom(AdvancedQueryPlan.class).join(HqlHelper.currTable, "entity", "e");
        helper.eq("e", "className", entityClassFullName).orderBy("createDate", OrderType.desc);
        helper.fetchWithTable(HqlHelper.currTable, "id", "dodo_id");
        helper.fetchWithTable(HqlHelper.currTable, "planTitle", "planTitle");
        helper.fetchWithTable(HqlHelper.currTable, "createDate", "createDate");
        Records records = helperService.getRecords(helper, Boolean.FALSE);
        for (Map<String, Object> map : records.getRawData()) {
            map.put("createDate", CommonUtil.getSpecialDateStr((Date) map.get("createDate"), "yyyy-MM-dd HH:mm:ss"));
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("code", 0);
        dataMap.put("msg", "");
        dataMap.put("count", records.size());
        dataMap.put("data", records.getRawData());
        return dataMap;
    }

    @RequestMapping("/get_query_plan.jhtml")
    @ResponseBody
    public RespData getQueryPlan(Model model, HttpServletRequest request, String entityClassFullName, String queryPlanId) {
        HqlHelper helper = HqlHelper.queryFrom(AdvancedQueryPlan.class);
        helper.fetch("planDetail").eq("id", queryPlanId).orderBy("createDate", OrderType.desc);
        Record record = helperService.getRecord(helper);
        String planDetail = record.get("planDetail");
        Map jsonMap = null;
        try {
            jsonMap = JacksonUtil.toObject(planDetail, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonMap == null) {
            return RespData.fail("Read From Json Fail.");
        }

        return RespData.success(jsonMap);
    }

    @RequestMapping("/changepwd_post.jhtml")
    @ResponseBody
    public Map<String, Object> changepwd_post(Model model, HttpServletRequest request, String oldPassword,
            String newPassword, String confirmNewPassword) {
        Admin admin = (Admin) securityService.getLoginPrincipal();

        HttpSession session = request.getSession();
        String changePwdSalt = (String) session.getAttribute(CHANGE_PWD_SALT_IN_SESSION);
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("status", "fail");
        retMap.put("message",
                SpringUtil.getMessageBack("dodo.common.header.i18n.changepwd.error", new Object[0], request));

        oldPassword = StringUtils.defaultIfBlank(oldPassword, "").trim();
        newPassword = StringUtils.defaultIfBlank(newPassword, "").trim();
        confirmNewPassword = StringUtils.defaultIfBlank(confirmNewPassword, "").trim();

        if (!CaptchaUtil.validateJCaptcha(request)) {
            retMap.put("message", SpringUtil.getMessageBack("dodo.common.login.i18n.checkcode", new Object[0], request)
                    + SpringUtil.getMessageBack("dodo.loginlog.login.log.wrong", new Object[0], request));
            return retMap;
        }

        if (!DigestUtils.md5Hex(admin.getAdminPassword() + changePwdSalt).equals(oldPassword)) {
            retMap.put("message",
                    SpringUtil.getMessageBack("dodo.common.header.i18n.changepwd.oldpwd", new Object[0], request)
                            + SpringUtil.getMessageBack("dodo.loginlog.login.log.wrong", new Object[0], request));
            return retMap;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            retMap.put("message",
                    SpringUtil.getMessageBack("dodo.common.header.i18n.changepwd.newpwd.error", new Object[0], request));
            return retMap;
        }

        HqlHelper helper = HqlHelper.queryFrom(Admin.class);
        helper.update("adminPassword", DigestUtils.md5Hex(newPassword + DodoCommonConfigUtil.passwordSalt));
        helper.eq("id", admin.getId());

        helperService.update(helper);

        retMap.put("status", "success");
        retMap.put("message", SpringUtil.getMessageBack("dodo.infotip.update.success",
                new Object[] { SpringUtil.getMessageBack("dodo.common.login.i18n.userpwd", new Object[0], request) },
                request));
        session.invalidate();
        return retMap;
    }

    @RequestMapping("/main.jhtml")
    public String main(Model model, HttpServletRequest request) {
        Admin admin = (Admin) securityService.getLoginPrincipal();
        DodoTree menuInfoTree = admin.getMenuInfoTree();
        List<DodoTreeNode> rootNodes = menuInfoTree.getRootNodes();
        for (DodoTreeNode rooTreeNode : rootNodes) {
            dealMenuNameKey(rooTreeNode, request);
        }
        model.addAttribute("menuInfoTree", menuInfoTree);
        return "page_main";
    }

    private void dealMenuNameKey(DodoTreeNode node, HttpServletRequest request) {
        if (StringUtils.isNotBlank(node.getNameKey())) {
            node.setName(SpringUtil.getMessageBack(node.getNameKey(), request));
        }
        if (node.isLeaf()) {
            return;
        }
        for (DodoTreeNode childNode : node.getChildList()) {
            dealMenuNameKey(childNode, request);
        }
    }

    @RequestMapping("/header.jhtml")
    public String header(Model model, HttpServletRequest request) {
        Admin admin = (Admin) securityService.getLoginPrincipal();
        DodoTree menuInfoTree = admin.getMenuInfoTree();
        List<DodoTreeNode> rootNodes = menuInfoTree.getRootNodes();
        for (DodoTreeNode rooTreeNode : rootNodes) {
            dealMenuNameKey(rooTreeNode, request);
        }
        model.addAttribute("menuInfoTree", menuInfoTree);
        return "page_header";
    }

    @RequestMapping("/left_{menuId}.jhtml")
    public String left(@PathVariable String menuId, Model model, HttpServletRequest request) {
        Admin admin = (Admin) securityService.getLoginPrincipal();
        DodoTree menuInfoTree = admin.getMenuInfoTree();
        List<DodoTreeNode> rootNodes = menuInfoTree.getRootNodes();
        for (DodoTreeNode rooTreeNode : rootNodes) {
            dealMenuNameKey(rooTreeNode, request);
        }
        if ("dodo".equals(menuId)) {
            try {
                model.addAttribute("menuInfoTree", JacksonUtil.toJackson(menuInfoTree.getValidTreeNode()));
            } catch (JsonProcessingException e) {
                model.addAttribute("menuInfoTree", null);
            }
        } else if ("whole".equals(menuId)) {
            model.addAttribute("menuInfoTree", menuInfoTree);
        } else {
            model.addAttribute("menuInfoTree", menuInfoTree.getDodoTreeNodeById(menuId));
        }
        return "page_left";
    }

    @RequestMapping("/middle.jhtml")
    public String middle(Model model) {
        return "page_middle";
    }

    @RequestMapping("/index.jhtml")
    public String index(Model model) {
        Properties props = System.getProperties();
        Runtime runtime = Runtime.getRuntime();
        long freeMemoery = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - freeMemoery;
        long maxMemory = runtime.maxMemory();
        long useableMemory = maxMemory - totalMemory + freeMemoery;
        model.addAttribute("props", props);
        model.addAttribute("freeMemoery", freeMemoery);
        model.addAttribute("totalMemory", totalMemory);
        model.addAttribute("usedMemory", usedMemory);
        model.addAttribute("maxMemory", maxMemory);
        model.addAttribute("useableMemory", useableMemory);
        return "page_index";
    }

    @RequestMapping("/footer.jhtml")
    public String footer(Model model) {
        return "page_footer";
    }
}