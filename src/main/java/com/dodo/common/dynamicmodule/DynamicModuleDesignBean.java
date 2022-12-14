package com.dodo.common.dynamicmodule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dodo.privilege.entity.dynmodule_4.config_1.ModuleField;

/**
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
public class DynamicModuleDesignBean {
    private String                   menuSELECT;
    private String                   updateModuleEntityId;
    private String                   name;
    private String                   beforeConditionSELECT;
    private String                   afterConditionSELECT;
    private String                   execSql;

    private Map<String, ModuleField> sameModuleField = new HashMap<String, ModuleField>();

    private List<String>             fieldName       = new ArrayList<String>();
    private List<String>             fieldType       = new ArrayList<String>();
    private List<String>             fieldLabel      = new ArrayList<String>();
    private List<String>             linkText        = new ArrayList<String>();
    private List<String>             jumpLink        = new ArrayList<String>();
    private List<String>             valueList       = new ArrayList<String>();
    private List<String>             valueIndex      = new ArrayList<String>();
    private List<Boolean>            fieldIsShow     = new ArrayList<Boolean>();
    private List<String>             fieldQueryType  = new ArrayList<String>();

    private List<String>             btnName         = new ArrayList<String>();
    private List<String>             btnLabel        = new ArrayList<String>();
    private List<String>             showCond        = new ArrayList<String>();
    private List<String>             btnStyle        = new ArrayList<String>();
    private List<String>             btnEvent        = new ArrayList<String>();
    private List<String>             btnModel        = new ArrayList<String>();
    private List<String>             ajaxTip         = new ArrayList<String>();
    private List<String>             ajaxTipStyle    = new ArrayList<String>();
    private List<String>             formModel       = new ArrayList<String>();
    private List<String>             paramValueField = new ArrayList<String>();
    private List<String>             paramName       = new ArrayList<String>();
    private List<String>             btnUrl          = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public String getBeforeConditionSELECT() {
        return beforeConditionSELECT;
    }

    public String getAfterConditionSELECT() {
        return afterConditionSELECT;
    }

    public String getExecSql() {
        return execSql;
    }

    public List<String> getFieldLabel() {
        return fieldLabel;
    }

    public List<Boolean> getFieldIsShow() {
        return fieldIsShow;
    }

    public List<String> getFieldQueryType() {
        return fieldQueryType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeforeConditionSELECT(String beforeConditionSELECT) {
        this.beforeConditionSELECT = beforeConditionSELECT;
    }

    public void setAfterConditionSELECT(String afterConditionSELECT) {
        this.afterConditionSELECT = afterConditionSELECT;
    }

    public void setExecSql(String execSql) {
        this.execSql = execSql;
    }

    public void setFieldLabel(List<String> fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public void setFieldIsShow(List<Boolean> fieldIsShow) {
        this.fieldIsShow = fieldIsShow;
    }

    public void setFieldQueryType(List<String> fieldQueryType) {
        this.fieldQueryType = fieldQueryType;
    }

    public String getUpdateModuleEntityId() {
        return updateModuleEntityId;
    }

    public void setUpdateModuleEntityId(String updateModuleEntityId) {
        this.updateModuleEntityId = updateModuleEntityId;
    }

    public List<String> getFieldName() {
        return fieldName;
    }

    public List<String> getFieldType() {
        return fieldType;
    }

    public void setFieldName(List<String> fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldType(List<String> fieldType) {
        this.fieldType = fieldType;
    }

    public List<String> getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(List<String> jumpLink) {
        this.jumpLink = jumpLink;
    }

    public String getMenuSELECT() {
        return menuSELECT;
    }

    public void setMenuSELECT(String menuSELECT) {
        this.menuSELECT = menuSELECT;
    }

    public List<String> getBtnName() {
        return btnName;
    }

    public void setBtnName(List<String> btnName) {
        this.btnName = btnName;
    }

    public List<String> getBtnLabel() {
        return btnLabel;
    }

    public void setBtnLabel(List<String> btnLabel) {
        this.btnLabel = btnLabel;
    }

    public List<String> getBtnEvent() {
        return btnEvent;
    }

    public void setBtnEvent(List<String> btnEvent) {
        this.btnEvent = btnEvent;
    }

    public List<String> getAjaxTip() {
        return ajaxTip;
    }

    public void setAjaxTip(List<String> ajaxTip) {
        this.ajaxTip = ajaxTip;
    }

    public List<String> getParamValueField() {
        return paramValueField;
    }

    public void setParamValueField(List<String> paramValueField) {
        this.paramValueField = paramValueField;
    }

    public List<String> getParamName() {
        return paramName;
    }

    public void setParamName(List<String> paramName) {
        this.paramName = paramName;
    }

    public List<String> getBtnUrl() {
        return btnUrl;
    }

    public void setBtnUrl(List<String> btnUrl) {
        this.btnUrl = btnUrl;
    }

    public List<String> getBtnModel() {
        return btnModel;
    }

    public void setBtnModel(List<String> btnModel) {
        this.btnModel = btnModel;
    }

    public List<String> getShowCond() {
        return showCond;
    }

    public void setShowCond(List<String> showCond) {
        this.showCond = showCond;
    }

    public List<String> getBtnStyle() {
        return btnStyle;
    }

    public void setBtnStyle(List<String> btnStyle) {
        this.btnStyle = btnStyle;
    }

    public Map<String, ModuleField> getSameModuleField() {
        return sameModuleField;
    }

    public void setSameModuleField(Map<String, ModuleField> sameModuleField) {
        this.sameModuleField = sameModuleField;
    }

    public List<String> getAjaxTipStyle() {
        return ajaxTipStyle;
    }

    public void setAjaxTipStyle(List<String> ajaxTipStyle) {
        this.ajaxTipStyle = ajaxTipStyle;
    }

    public List<String> getFormModel() {
        return formModel;
    }

    public void setFormModel(List<String> formModel) {
        this.formModel = formModel;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public List<String> getValueIndex() {
        return valueIndex;
    }

    public void setValueIndex(List<String> valueIndex) {
        this.valueIndex = valueIndex;
    }

    public List<String> getLinkText() {
        return linkText;
    }

    public void setLinkText(List<String> linkText) {
        this.linkText = linkText;
    }

    @Override
    public String toString() {
        return "DynamicModuleDesignBean [menuSELECT=" + menuSELECT + ", updateModuleEntityId=" + updateModuleEntityId
                + ", name=" + name + ", beforeConditionSELECT=" + beforeConditionSELECT + ", afterConditionSELECT="
                + afterConditionSELECT + ", execSql=" + execSql + ", sameModuleField=" + sameModuleField
                + ", fieldName=" + fieldName + ", fieldType=" + fieldType + ", fieldLabel=" + fieldLabel
                + ", jumpLink=" + jumpLink + ", fieldIsShow=" + fieldIsShow + ", fieldQueryType=" + fieldQueryType
                + ", btnName=" + btnName + ", btnLabel=" + btnLabel + ", showCond=" + showCond + ", btnStyle="
                + btnStyle + ", btnEvent=" + btnEvent + ", btnModel=" + btnModel + ", ajaxTip=" + ajaxTip
                + ", ajaxTipStyle=" + ajaxTipStyle + ", formModel=" + formModel + ", paramValueField="
                + paramValueField + ", paramName=" + paramName + ", btnUrl=" + btnUrl + "]";
    }
}
