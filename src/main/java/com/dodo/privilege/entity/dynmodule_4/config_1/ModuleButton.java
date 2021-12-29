package com.dodo.privilege.entity.dynmodule_4.config_1;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.field.DodoShowColumn;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.framework.entity.BaseEntity;
import com.dodo.privilege.enums.ModuleButtonEvent;
import com.dodo.privilege.enums.ModuleButtonModel;

/**
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
@Entity
@DynamicInsert
@DodoEntity(
        nameKey = "dodo.privilege.dynmodule.config.ModuleButton.entityKey",
        actions = { DodoAction.VIEW, DodoAction.EXPORT },
        levelOne = @DodoMenu(nameKey = "dodo.privilege.dynmodule.menuNameKey", sortSeq = 4),
        levelTwo = @DodoMenu(nameKey = "dodo.privilege.dynmodule.config.menuNameKey", sortSeq = 1),
        levelThree = @DodoMenu(nameKey = "dodo.privilege.dynmodule.config.ModuleButton.menuNameKey", sortSeq = 3))
public class ModuleButton extends BaseEntity {
    private static final long serialVersionUID = -2310210720000342692L;

    @DodoField(
            sortSeq = 0,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.moduleEntity",
            isnullable = false,
            queryOnList = true)
    private ModuleEntity      moduleEntity;

    @DodoField(
            sortSeq = 1,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.btnName",
            isnullable = false)
    @DodoShowColumn(sortSeq = 0)
    private String            btnName;

    @DodoField(
            sortSeq = 2,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.btnLabel",
            isnullable = false,
            maxLength = 64)
    @DodoShowColumn(sortSeq = 1)
    private String            btnLabel;

    @DodoField(
            sortSeq = 3,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.showCond",
            isnullable = false,
            maxLength = 200)
    private String            showCond;

    @DodoField(
            sortSeq = 3,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.btnStyle",
            isnullable = false,
            maxLength = 32)
    private String            btnStyle;

    @DodoField(
            sortSeq = 4,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.btnModel",
            isnullable = false)
    private ModuleButtonModel btnModel;

    @DodoField(
            sortSeq = 5,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.btnEvent",
            isnullable = false)
    private ModuleButtonEvent btnEvent;

    @DodoField(
            sortSeq = 6,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.ajaxTip",
            isnullable = false,
            maxLength = 64)
    private String            ajaxTip;

    @DodoField(
            sortSeq = 7,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.paramValueField",
            isnullable = false,
            maxLength = 64)
    private String            paramValueField;

    @DodoField(
            sortSeq = 8,
            nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.paramName",
            isnullable = false,
            maxLength = 64)
    private String            paramName;

    @DodoField(sortSeq = 9, nameKey = "dodo.privilege.dynmodule.config.ModuleButton.namekey.btnUrl", editable = false)
    private String            btnUrl;

    @ManyToOne
    public ModuleEntity getModuleEntity() {
        return moduleEntity;
    }

    @Column(length = 64)
    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    @Column(length = 32)
    public String getBtnLabel() {
        return btnLabel;
    }

    public void setBtnLabel(String btnLabel) {
        this.btnLabel = btnLabel;
    }

    @Column(length = 3)
    @Convert(converter = ModuleButtonEvent.Converter.class)
    public ModuleButtonEvent getBtnEvent() {
        return btnEvent;
    }

    public void setBtnEvent(ModuleButtonEvent btnEvent) {
        this.btnEvent = btnEvent;
    }

    @Column(length = 128)
    public String getAjaxTip() {
        return ajaxTip;
    }

    public void setAjaxTip(String ajaxTip) {
        this.ajaxTip = ajaxTip;
    }

    @Column(length = 128)
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Column(length = 128)
    public String getParamValueField() {
        return paramValueField;
    }

    public void setParamValueField(String paramValueField) {
        this.paramValueField = paramValueField;
    }

    @Column(length = 128)
    public String getBtnUrl() {
        return btnUrl;
    }

    public void setBtnUrl(String btnUrl) {
        this.btnUrl = btnUrl;
    }

    public void setModuleEntity(ModuleEntity moduleEntity) {
        this.moduleEntity = moduleEntity;
    }

    @Column(length = 3)
    @Convert(converter = ModuleButtonModel.Converter.class)
    public ModuleButtonModel getBtnModel() {
        return btnModel;
    }

    public void setBtnModel(ModuleButtonModel btnModel) {
        this.btnModel = btnModel;
    }

    @Column(length = 200)
    public String getShowCond() {
        return showCond;
    }

    public void setShowCond(String showCond) {
        this.showCond = showCond;
    }

    @Column(length = 32)
    public String getBtnStyle() {
        return btnStyle;
    }

    public void setBtnStyle(String btnStyle) {
        this.btnStyle = btnStyle;
    }

}
