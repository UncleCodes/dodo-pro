package com.dodo.privilege.action.frame;

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
import com.dodo.common.database.hql.HqlHelper;
import com.dodo.common.framework.bean.tree.DodoTree;
import com.dodo.common.framework.bean.tree.DodoTreeNode;
import com.dodo.common.framework.service.HqlHelperService;
import com.dodo.privilege.entity.admin_1.base_1.Admin;
import com.dodo.privilege.security.DodoSecurityService;
import com.dodo.utils.JacksonUtil;
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
    private HqlHelperService    hqlHelperService;

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

        hqlHelperService.update(helper);

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