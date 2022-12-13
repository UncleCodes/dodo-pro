package com.dodo.privilege.entity.admin_1.config_5;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.check.DodoUniqueGroup;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.field.FileStyle;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.framework.entity.BaseEntity;
import com.dodo.privilege.enums.ExtendModelFieldType;

/**
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
@javax.persistence.Entity
@DynamicInsert
@DodoEntity(
        nameKey = "dodo.privilege.admin.config.FormModelField.entityKey",
        actions = { DodoAction.ALL },
        levelOne = @DodoMenu(nameKey = "dodo.privilege.admin.menuNameKey", sortSeq = 1),
        levelTwo = @DodoMenu(nameKey = "dodo.privilege.admin.config.menuNameKey", sortSeq = 5),
        levelThree = @DodoMenu(nameKey = "dodo.privilege.admin.config.FormModelField.menuNameKey", sortSeq = 8))
@DodoUniqueGroup(fieldNames = { "formModel", "fieldName" })
public class FormModelField extends BaseEntity implements java.io.Serializable {

    private static final long    serialVersionUID = 8005953822890520170L;

    @DodoField(
            sortSeq = 1,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.formModel",
            queryParams = "eq(\"isUse\",Boolean.TRUE)",
            isnullable = false,
            editable = false,
            queryOnList = true,
            isSetDefault = false,
            isPopup = true)
    private FormModel            formModel;

    @DodoField(
            sortSeq = 2,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.fieldName",
            isnullable = false,
            editable = false,
            queryOnList = true,
            isSetDefault = false,
            regExp = "[a-zA-Z]{3,}",
            regExpTip = "Only:[a-zA-Z]{3,}")
    private String               fieldName;

    @DodoField(
            sortSeq = 3,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.fieldType",
            isnullable = false,
            editable = false)
    private ExtendModelFieldType fieldType;

    @DodoField(sortSeq = 4, nameKey = "dodo.privilege.admin.config.FormModelField.namekey.showName", queryOnList = true)
    private String               showName;

    @DodoField(
            sortSeq = 5,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.showNameKey",
            infoTipKey = "dodo.privilege.admin.config.FormModelField.infoTip.showNameKey")
    private String               showNameKey;

    @DodoField(sortSeq = 8, nameKey = "dodo.privilege.admin.config.FormModelField.namekey.nullable", isnullable = false)
    private Boolean              nullable;

    @DodoField(
            sortSeq = 9,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.minLength",
            showOnField = "fieldType",
            showOnValue = "STRING,TEXTAREA")
    private Integer              minLength;

    @DodoField(
            sortSeq = 10,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.maxLength",
            showOnField = "fieldType",
            showOnValue = "STRING,TEXTAREA")
    private Integer              maxLength;

    @DodoField(
            sortSeq = 11,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.isEmail",
            showOnField = "fieldType",
            showOnValue = "STRING",
            isnullable = false)
    private Boolean              isEmail;

    @DodoField(
            sortSeq = 12,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.isMobile",
            showOnField = "fieldType",
            showOnValue = "STRING",
            isnullable = false)
    private Boolean              isMobile;

    @DodoField(
            sortSeq = 13,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.isUrl",
            showOnField = "fieldType",
            showOnValue = "STRING",
            isnullable = false)
    private Boolean              isUrl;

    @DodoField(
            sortSeq = 14,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.isCreditcard",
            showOnField = "fieldType",
            showOnValue = "STRING",
            isnullable = false)
    private Boolean              isCreditcard;

    @DodoField(
            sortSeq = 15,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.isIp",
            showOnField = "fieldType",
            showOnValue = "STRING",
            isnullable = false)
    private Boolean              isIp;

    @DodoField(
            sortSeq = 16,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.minValue",
            showOnField = "fieldType",
            showOnValue = "NUMBER,DIGITS")
    private Integer              minValue;

    @DodoField(
            sortSeq = 17,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.maxValue",
            showOnField = "fieldType",
            showOnValue = "NUMBER,DIGITS")
    private Integer              maxValue;

    @DodoField(
            sortSeq = 20,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.maxFileSize",
            showOnField = "fieldType",
            showOnValue = "SINGLEFILE,MULTIFILE,DOC,VIDEO",
            infoTipKey = "dodo.privilege.admin.config.FormModelField.infoTip.maxFileSize",
            isnullable = false)
    private Integer              maxFileSize;

    @DodoField(
            sortSeq = 21,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.fileExts",
            showOnField = "fieldType",
            showOnValue = "SINGLEFILE,MULTIFILE,DOC,VIDEO",
            infoTipKey = "dodo.privilege.admin.config.FormModelField.infoTip.fileExts",
            isnullable = false)
    private String               fileExts;

    @DodoField(
            sortSeq = 22,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.valueList",
            showOnField = "fieldType",
            showOnValue = "SELECT,RADIO,CHECKBOX",
            infoTipKey = "dodo.privilege.admin.config.FormModelField.infoTip.valueList")
    private String               valueList;

    @DodoField(
            sortSeq = 23,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.labelList",
            showOnField = "fieldType",
            showOnValue = "SELECT,RADIO,CHECKBOX",
            infoTipKey = "dodo.privilege.admin.config.FormModelField.infoTip.labelList")
    private String               labelList;

    @DodoField(
            sortSeq = 24,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.fileStyle",
            showOnField = "fieldType",
            showOnValue = "SINGLEFILE,MULTIFILE",
            isnullable = false)
    private FileStyle            fileStyle;

    @DodoField(
            sortSeq = 25,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.ossBucket",
            showOnField = "fileStyle",
            showOnValue = "OnlyPath")
    private String               ossBucket;

    @DodoField(sortSeq = 27, nameKey = "dodo.privilege.admin.config.FormModelField.namekey.infoTip")
    private String               infoTip;

    @DodoField(
            sortSeq = 29,
            nameKey = "dodo.privilege.admin.config.FormModelField.namekey.infoTipKey",
            infoTipKey = "dodo.privilege.admin.config.FormModelField.infoTip.infoTipKey")
    private String               infoTipKey;

    @Override
    public void onSave() {
        super.onSave();
        if (fileStyle == null) {
            fileStyle = FileStyle.OnlyPath;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (fileStyle == null) {
            fileStyle = FileStyle.OnlyPath;
        }
    }

    @OneToOne
    public FormModel getFormModel() {
        return formModel;
    }

    @Column(length = 64)
    public String getFieldName() {
        return fieldName;
    }

    @Column(length = 3)
    @Convert(converter = ExtendModelFieldType.Converter.class)
    public ExtendModelFieldType getFieldType() {
        return fieldType;
    }

    @Column(length = 64)
    public String getShowName() {
        return showName;
    }

    @Column(length = 128)
    public String getShowNameKey() {
        return showNameKey;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public Boolean getIsEmail() {
        return isEmail;
    }

    public Boolean getIsMobile() {
        return isMobile;
    }

    public Boolean getIsUrl() {
        return isUrl;
    }

    public Boolean getIsCreditcard() {
        return isCreditcard;
    }

    public Boolean getIsIp() {
        return isIp;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    @Column(length = 128)
    public String getInfoTip() {
        return infoTip;
    }

    @Column(length = 128)
    public String getInfoTipKey() {
        return infoTipKey;
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    @Column(length = 254)
    public String getFileExts() {
        return fileExts;
    }

    public void setFormModel(FormModel formModel) {
        this.formModel = formModel;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldType(ExtendModelFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setShowNameKey(String showNameKey) {
        this.showNameKey = showNameKey;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public void setIsEmail(Boolean isEmail) {
        this.isEmail = isEmail;
    }

    public void setIsMobile(Boolean isMobile) {
        this.isMobile = isMobile;
    }

    public void setIsUrl(Boolean isUrl) {
        this.isUrl = isUrl;
    }

    public void setIsCreditcard(Boolean isCreditcard) {
        this.isCreditcard = isCreditcard;
    }

    public void setIsIp(Boolean isIp) {
        this.isIp = isIp;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public void setInfoTip(String infoTip) {
        this.infoTip = infoTip;
    }

    public void setInfoTipKey(String infoTipKey) {
        this.infoTipKey = infoTipKey;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public void setFileExts(String fileExts) {
        this.fileExts = fileExts;
    }

    @Column(length = 254)
    public String getValueList() {
        return valueList;
    }

    @Column(length = 1024)
    public String getLabelList() {
        return labelList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }

    public void setLabelList(String labelList) {
        this.labelList = labelList;
    }

    @Column(length = 3)
    @Convert(converter = FileStyle.Converter.class)
    public FileStyle getFileStyle() {
        return fileStyle;
    }

    public void setFileStyle(FileStyle fileStyle) {
        this.fileStyle = fileStyle;
    }

    @Column(length = 16)
    public String getOssBucket() {
        return ossBucket;
    }

    public void setOssBucket(String ossBucket) {
        this.ossBucket = ossBucket;
    }
}
