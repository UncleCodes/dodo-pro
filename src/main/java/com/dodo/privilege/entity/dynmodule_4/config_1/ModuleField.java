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
import com.dodo.common.annotation.report.ReportFieldType;
import com.dodo.common.annotation.report.ReportQueryType;
import com.dodo.common.framework.entity.BaseEntity;

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
        nameKey = "dodo.privilege.dynmodule.config.ModuleField.entityKey",
        actions = { DodoAction.VIEW, DodoAction.UPDATE, DodoAction.EXPORT },
        levelOne = @DodoMenu(nameKey = "dodo.privilege.dynmodule.menuNameKey", sortSeq = 4),
        levelTwo = @DodoMenu(nameKey = "dodo.privilege.dynmodule.config.menuNameKey", sortSeq = 1),
        levelThree = @DodoMenu(nameKey = "dodo.privilege.dynmodule.config.ModuleField.menuNameKey", sortSeq = 2))
public class ModuleField extends BaseEntity {
    private static final long serialVersionUID = -4443407897694944602L;

    @DodoField(
            sortSeq = 1,
            nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.moduleEntity",
            isnullable = false,
            editable = false,
            queryOnList = true)
    private ModuleEntity      moduleEntity;

    @DodoField(
            sortSeq = 3,
            nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.queryField",
            isnullable = false,
            editable = false)
    @DodoShowColumn(sortSeq = 0)
    private String            queryField;

    @DodoField(
            sortSeq = 5,
            nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.valueIndex",
            isnullable = false,
            editable = false)
    private String            valueIndex;

    @DodoField(
            sortSeq = 7,
            nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.showName",
            isnullable = false,
            maxLength = 64)
    @DodoShowColumn(sortSeq = 1)
    private String            showName;

    @DodoField(
            sortSeq = 9,
            nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.fieldType",
            isnullable = false,
            editable = false)
    private ReportFieldType   fieldType;

    @DodoField(sortSeq = 11, nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.queryType")
    private ReportQueryType   queryType;

    @DodoField(sortSeq = 13, nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.isShow", isnullable = false)
    private Boolean           isShow;

    @DodoField(sortSeq = 14, nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.linkText", editable = false)
    private String            linkText;

    @DodoField(
            sortSeq = 15,
            nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.jumpLink",
            editable = false,
            infoTipKey = "dodo.privilege.dynmodule.config.ModuleField.infoTip.jumpLink")
    private String            jumpLink;

    @DodoField(sortSeq = 17, nameKey = "dodo.privilege.dynmodule.config.ModuleField.namekey.valueList", maxLength = 200)
    private String            valueList;

    @ManyToOne
    public ModuleEntity getModuleEntity() {
        return moduleEntity;
    }

    @Column(length = 64)
    public String getQueryField() {
        return queryField;
    }

    @Column(length = 64)
    public String getShowName() {
        return showName;
    }

    @Column(length = 3)
    @Convert(converter = ReportFieldType.Converter.class)
    public ReportFieldType getFieldType() {
        return fieldType;
    }

    @Column(length = 3)
    @Convert(converter = ReportQueryType.Converter.class)
    public ReportQueryType getQueryType() {
        return queryType;
    }

    public void setModuleEntity(ModuleEntity moduleEntity) {
        this.moduleEntity = moduleEntity;
    }

    public void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setFieldType(ReportFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public void setQueryType(ReportQueryType queryType) {
        this.queryType = queryType;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    @Column(length = 128)
    public String getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink;
    }

    @Column(length = 200)
    public String getValueList() {
        return valueList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }

    @Column(length = 32)
    public String getValueIndex() {
        return valueIndex;
    }

    public void setValueIndex(String valueIndex) {
        this.valueIndex = valueIndex;
    }

    @Column(length = 128)
    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }
}
