package com.dodo.privilege.formmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 注释内容<br/>
 * 
 * @author djh
 * 
 * @version
 */
public class FormModelProcessParams implements Serializable {
    private static final long           serialVersionUID     = 6928637838827346328L;
    // 字段配置
    private List<FormModelProcessField> formModelFields      = new ArrayList<FormModelProcessField>();
    // 提交前的提示信息
    private String                      submitTip            = "Do you want to continue?";
    // 表单模型的名称
    private String                      formModelName        = "";
    // 表单提交的地址
    private String                      formModelPostUrl     = "";
    // 表单模型的视图
    private String                      formModelProcessView = "formmodel_process";
    // 自动添加的参数
    private Map<String, Object>         autoAddParams        = new HashMap<String, Object>();
    // 默认值
    private Map<String, Object>         valuesMap            = new HashMap<String, Object>();

    public List<FormModelProcessField> getFormModelFields() {
        return formModelFields;
    }

    public void setFormModelFields(List<FormModelProcessField> formModelFields) {
        this.formModelFields = formModelFields;
    }

    public String getSubmitTip() {
        return submitTip;
    }

    public void setSubmitTip(String submitTip) {
        this.submitTip = submitTip;
    }

    public String getFormModelName() {
        return formModelName;
    }

    public void setFormModelName(String formModelName) {
        this.formModelName = formModelName;
    }

    public String getFormModelPostUrl() {
        return formModelPostUrl;
    }

    public void setFormModelPostUrl(String formModelPostUrl) {
        this.formModelPostUrl = formModelPostUrl;
    }

    public String getFormModelProcessView() {
        return formModelProcessView;
    }

    public void setFormModelProcessView(String formModelProcessView) {
        this.formModelProcessView = formModelProcessView;
    }

    public Map<String, Object> getAutoAddParams() {
        return autoAddParams;
    }

    public void setAutoAddParams(Map<String, Object> autoAddParams) {
        this.autoAddParams = autoAddParams;
    }

    public Map<String, Object> getValuesMap() {
        return valuesMap;
    }

    public void setValuesMap(Map<String, Object> valuesMap) {
        this.valuesMap = valuesMap;
    }

}
