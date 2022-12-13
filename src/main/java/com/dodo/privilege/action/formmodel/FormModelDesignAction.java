package com.dodo.privilege.action.formmodel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.common.framework.service.HqlHelperService;
import com.dodo.privilege.formmodel.FormModelProcessParams;
import com.dodo.privilege.formmodel.FormModelProcesser;
import com.dodo.utils.RespData;

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
@RequestMapping("${dodo.backmanage.view.rootPath}/formmodeldesign")
public class FormModelDesignAction {

    @Autowired
    private HqlHelperService helperService;

    @RequestMapping("/vide_design.jhtml")
    public String to_sign_review_info(Model model, String entityId) {
        // 提交地址
        String formModelPostUrl = "{rootPath}/formmodeldesign/vide_design_post.jhtml";

        // 自定义参数
        Map<String, Object> autoAddParams = new HashMap<String, Object>();
        autoAddParams.put("selfParam_01", "selfParam_01");
        autoAddParams.put("selfParam_02", "selfParam_02");
        autoAddParams.put("selfParam_03", "selfParam_03");

        FormModelProcessParams processParams = FormModelProcesser.processFormModelParams(helperService, entityId, "0",
                formModelPostUrl, autoAddParams, null);

        model.addAttribute(FormModelProcesser.MODEL_KEY_IN_VIEW, processParams);

        return (String) processParams.getFormModelProcessView();
    }

    @RequestMapping("/vide_design_post.jhtml")
    @ResponseBody
    public RespData sign_review_info() {
        return RespData.success("操作成功", null);
    }
}