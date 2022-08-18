package com.dodo.privilege.action.formmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.common.database.hql.HqlHelper;
import com.dodo.common.framework.bean.pager.PageModel.OrderType;
import com.dodo.common.framework.service.HqlHelperService;
import com.dodo.privilege.entity.admin_1.config_5.FormModel;
import com.dodo.privilege.entity.admin_1.config_5.FormModelField;
import com.dodo.utils.FormModelUtil;
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
        HqlHelper helper = HqlHelper.queryFrom(FormModelField.class);
        // 字段
        helper.join(HqlHelper.currTable, "formModel", "e")
                .eq("e", "id", entityId)
                .fetch("fieldName", "showName", "showNameKey", "fieldType", "nullable", "minLength", "maxLength",
                        "isEmail", "isMobile", "isUrl", "isCreditcard", "isIp", "minValue", "maxValue", "infoTip",
                        "infoTipKey", "maxFileSize", "fileExts", "valueList", "labelList", "fileStyle", "ossBucket")
                .orderBy("sortSeq", OrderType.asc);
        List<Map<String, Object>> formModelFields = helperService.getRecords(helper, Boolean.FALSE).getRawData();

        // 提示信息
        String submitTip = "Do you want to continue?";

        // 模型名称
        helper.resetQueryFrom(FormModel.class);
        helper.eq("id", entityId);
        helper.fetch("modelName");
        String formModelName = helperService.getRecord(helper).get("modelName");

        // 提交地址
        String formModelPostUrl = "{rootPath}/formmodeldesign/vide_design_post.jhtml";

        // 自定义参数
        Map<String, Object> autoAddParams = new HashMap<String, Object>();
        autoAddParams.put("selfParam_01", "selfParam_01");
        autoAddParams.put("selfParam_02", "selfParam_02");
        autoAddParams.put("selfParam_03", "selfParam_03");

        Map<String, Object> formModelAddParams = FormModelUtil.prepareFormModelAddParams(formModelFields,
                formModelName, submitTip, formModelPostUrl, autoAddParams);

        model.addAllAttributes(formModelAddParams);

        return (String) formModelAddParams.get("formModelAddView");
    }

    @RequestMapping("/vide_design_post.jhtml")
    @ResponseBody
    public RespData sign_review_info() {
        return RespData.success("操作成功", null);
    }
}