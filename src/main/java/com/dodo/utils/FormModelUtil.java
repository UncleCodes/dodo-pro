/*
 * <strong>File   ：</strong>FormModelUtil.java <br/>
 * <strong>Project：</strong>galaxy-app-hr <br/>
 * <strong>Date   ：</strong>2022年8月4日 下午3:01:43 <br/>
 * Copyright © 2010-2016 IKaiHuo Corporation, All Rights Reserved
 * 
 */
package com.dodo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.dodo.common.database.data.Record;
import com.dodo.common.database.hql.HqlHelper;
import com.dodo.common.framework.bean.pager.PageModel.OrderType;
import com.dodo.common.framework.service.HqlHelperService;
import com.dodo.privilege.entity.admin_1.config_5.FormModel;
import com.dodo.privilege.entity.admin_1.config_5.FormModelField;
import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleButton;

/**
 * 
 * 注释内容<br/>
 * 
 * @author djh
 * 
 * @version
 */
public class FormModelUtil {
    public static Map<String, Object> prepareFormModelAddParams(HqlHelperService helperService, String formModelId,
            String moduleBtnId, String formModelPostUrl, Map<String, Object> autoAddParams) {

        HqlHelper helper = HqlHelper.queryFrom(FormModelField.class);
        // 字段
        helper.join(HqlHelper.currTable, "formModel", "e")
                .eq("e", "id", formModelId)
                .fetch("fieldName", "showName", "showNameKey", "fieldType", "nullable", "minLength", "maxLength",
                        "isEmail", "isMobile", "isUrl", "isCreditcard", "isIp", "minValue", "maxValue", "infoTip",
                        "infoTipKey", "maxFileSize", "fileExts", "valueList", "labelList", "fileStyle", "ossBucket")
                .orderBy("sortSeq", OrderType.asc);
        List<Map<String, Object>> formModelFields = helperService.getRecords(helper, Boolean.FALSE).getRawData();

        // 提示信息
        String submitTip = "";
        helper.resetQueryFrom(ModuleButton.class);
        helper.eq("id", moduleBtnId);
        helper.fetch("ajaxTip");
        Record submitRecord = helperService.getRecord(helper);
        if (submitRecord != null) {
            submitTip = submitRecord.get("ajaxTip");
        }
        if (StringUtils.isBlank(submitTip)) {
            submitTip = "Do you want to continue?";
        }

        // 模型名称
        helper.resetQueryFrom(FormModel.class);
        helper.eq("id", formModelId);
        helper.fetch("modelName");
        String formModelName = helperService.getRecord(helper).get("modelName");

        Map<String, Object> model = new HashMap<String, Object>();
        // 字段
        model.put("formModelFields", formModelFields);
        // 提示信息
        model.put("submitTip", submitTip);
        // 模型名称
        model.put("formModelName", formModelName);
        // 提交地址
        model.put("formModelPostUrl", formModelPostUrl);
        model.put("formModelAddView", "formmodel_toadd");
        model.put("autoAddParams", autoAddParams);

        return model;
    }
}
