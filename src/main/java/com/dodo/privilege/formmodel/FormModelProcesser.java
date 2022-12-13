package com.dodo.privilege.formmodel;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.dodo.common.annotation.field.FileStyle;
import com.dodo.common.database.data.Record;
import com.dodo.common.database.hql.HqlHelper;
import com.dodo.common.framework.bean.pager.PageModel.OrderType;
import com.dodo.common.framework.service.HqlHelperService;
import com.dodo.privilege.entity.admin_1.config_5.FormModel;
import com.dodo.privilege.entity.admin_1.config_5.FormModelField;
import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleButton;
import com.dodo.privilege.enums.ExtendModelFieldType;

/**
 * 
 * 注释内容<br/>
 * 
 * @author djh
 * 
 * @version
 */
public class FormModelProcesser {
    public static final String MODEL_KEY_IN_VIEW = "processParams";

    // 更新
    public static FormModelProcessParams processFormModelParams(HqlHelperService helperService, String formModelId,
            String moduleBtnId, String formModelPostUrl, Map<String, Object> autoAddParams,
            Map<String, Object> valuesMap) {
        FormModelProcessParams processParams = new FormModelProcessParams();
        processParams.setFormModelPostUrl(formModelPostUrl);
        if (valuesMap != null) {
            processParams.setValuesMap(valuesMap);
        }
        if (autoAddParams != null) {
            processParams.setAutoAddParams(autoAddParams);
        }

        HqlHelper helper = HqlHelper.queryFrom(FormModelField.class);
        // 字段
        helper.join(HqlHelper.currTable, "formModel", "e")
                .eq("e", "id", formModelId)
                .fetch("fieldName", "showName", "showNameKey", "fieldType", "nullable", "minLength", "maxLength",
                        "isEmail", "isMobile", "isUrl", "isCreditcard", "isIp", "minValue", "maxValue", "infoTip",
                        "infoTipKey", "maxFileSize", "fileExts", "valueList", "labelList", "fileStyle", "ossBucket")
                .orderBy("sortSeq", OrderType.asc);
        List<Map<String, Object>> formModelFields = helperService.getRecords(helper, Boolean.FALSE).getRawData();
        formModelFields.forEach(field -> {
            FormModelProcessField processField = new FormModelProcessField();
            processField.setFieldName((String) field.get("fieldName"));
            processField.setFieldType((ExtendModelFieldType) field.get("fieldType"));
            processField.setShowName((String) field.get("showName"));
            processField.setShowNameKey((String) field.get("showNameKey"));
            processField.setNullable((Boolean) field.get("nullable"));
            processField.setMinLength((Integer) field.get("minLength"));
            processField.setMaxLength((Integer) field.get("maxLength"));
            processField.setIsEmail((Boolean) field.get("isEmail"));
            processField.setIsMobile((Boolean) field.get("isMobile"));
            processField.setIsUrl((Boolean) field.get("isUrl"));
            processField.setIsCreditcard((Boolean) field.get("isCreditcard"));
            processField.setIsIp((Boolean) field.get("isIp"));
            processField.setMinValue((Integer) field.get("minValue"));
            processField.setMaxValue((Integer) field.get("maxValue"));
            processField.setMaxFileSize((Integer) field.get("maxFileSize"));
            processField.setFileExts((String) field.get("fileExts"));
            processField.setValueList((String) field.get("valueList"));
            processField.setLabelList((String) field.get("labelList"));
            processField.setFileStyle((FileStyle) field.get("fileStyle"));
            processField.setOssBucket((String) field.get("ossBucket"));
            processField.setInfoTip((String) field.get("infoTip"));
            processField.setInfoTipKey((String) field.get("infoTipKey"));
            processParams.getFormModelFields().add(processField);
        });

        // 提示信息
        String submitTip = "";
        helper.resetQueryFrom(ModuleButton.class);
        helper.eq("id", moduleBtnId);
        helper.fetch("ajaxTip");
        Record submitRecord = helperService.getRecord(helper);
        if (submitRecord != null) {
            submitTip = submitRecord.get("ajaxTip");
        }
        if (StringUtils.isNotBlank(submitTip)) {
            processParams.setSubmitTip(submitTip);
        }

        // 模型名称
        helper.resetQueryFrom(FormModel.class);
        helper.eq("id", formModelId);
        helper.fetch("modelName");
        String formModelName = helperService.getRecord(helper).get("modelName");
        processParams.setFormModelName(formModelName);

        return processParams;

    }
}
