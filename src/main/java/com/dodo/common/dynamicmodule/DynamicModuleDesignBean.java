package com.dodo.common.dynamicmodule;

import java.util.ArrayList;
import java.util.List;

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
    private String        menuSELECT;
    private String        updateModuleEntityId;
    private String        name;
    private String        beforeConditionSELECT;
    private String        afterConditionSELECT;
    private String        execSql;
    private List<String>  fieldName       = new ArrayList<String>();
    private List<String>  fieldType       = new ArrayList<String>();
    private List<String>  fieldLabel      = new ArrayList<String>();
    private List<String>  showCond        = new ArrayList<String>();
    private List<String>  jumpLink        = new ArrayList<String>();
    private List<Boolean> fieldIsShow     = new ArrayList<Boolean>();
    private List<String>  fieldQueryType  = new ArrayList<String>();

    private List<String>  btnName         = new ArrayList<String>();
    private List<String>  btnLabel        = new ArrayList<String>();
    private List<String>  btnEvent        = new ArrayList<String>();
    private List<String>  btnModel        = new ArrayList<String>();
    private List<String>  ajaxTip         = new ArrayList<String>();
    private List<String>  paramValueField = new ArrayList<String>();
    private List<String>  paramName       = new ArrayList<String>();
    private List<String>  btnUrl          = new ArrayList<String>();

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

    @Override
    public String toString() {
        return "DynamicModuleDesignBean [menuSELECT=" + menuSELECT + ", updateModuleEntityId=" + updateModuleEntityId
                + ", name=" + name + ", beforeConditionSELECT=" + beforeConditionSELECT + ", afterConditionSELECT="
                + afterConditionSELECT + ", execSql=" + execSql + ", fieldName=" + fieldName + ", fieldType="
                + fieldType + ", fieldLabel=" + fieldLabel + ", showCond=" + showCond + ", jumpLink=" + jumpLink
                + ", fieldIsShow=" + fieldIsShow + ", fieldQueryType=" + fieldQueryType + ", btnName=" + btnName
                + ", btnLabel=" + btnLabel + ", btnEvent=" + btnEvent + ", btnModel=" + btnModel + ", ajaxTip="
                + ajaxTip + ", paramValueField=" + paramValueField + ", paramName=" + paramName + ", btnUrl=" + btnUrl
                + "]";
    }
}
