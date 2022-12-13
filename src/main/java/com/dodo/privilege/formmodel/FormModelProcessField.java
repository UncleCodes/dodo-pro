package com.dodo.privilege.formmodel;

import com.dodo.common.annotation.field.FileStyle;
import com.dodo.privilege.entity.admin_1.config_5.FormModelField;
import com.dodo.privilege.enums.ExtendModelFieldType;

/**
 * @see {@link FormModelField}
 * */
public class FormModelProcessField implements java.io.Serializable {

    private static final long    serialVersionUID = -8354789699350581759L;

    private String               fieldName;

    private ExtendModelFieldType fieldType;

    private String               showName;

    private String               showNameKey;

    private Boolean              nullable;

    private Integer              minLength;

    private Integer              maxLength;

    private Boolean              isEmail;

    private Boolean              isMobile;

    private Boolean              isUrl;

    private Boolean              isCreditcard;

    private Boolean              isIp;

    private Integer              minValue;

    private Integer              maxValue;

    private Integer              maxFileSize;

    private String               fileExts;

    private String               valueList;

    private String               labelList;

    private FileStyle            fileStyle;

    private String               ossBucket;

    private String               infoTip;

    private String               infoTipKey;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public ExtendModelFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(ExtendModelFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowNameKey() {
        return showNameKey;
    }

    public void setShowNameKey(String showNameKey) {
        this.showNameKey = showNameKey;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(Boolean isEmail) {
        this.isEmail = isEmail;
    }

    public Boolean getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(Boolean isMobile) {
        this.isMobile = isMobile;
    }

    public Boolean getIsUrl() {
        return isUrl;
    }

    public void setIsUrl(Boolean isUrl) {
        this.isUrl = isUrl;
    }

    public Boolean getIsCreditcard() {
        return isCreditcard;
    }

    public void setIsCreditcard(Boolean isCreditcard) {
        this.isCreditcard = isCreditcard;
    }

    public Boolean getIsIp() {
        return isIp;
    }

    public void setIsIp(Boolean isIp) {
        this.isIp = isIp;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getFileExts() {
        return fileExts;
    }

    public void setFileExts(String fileExts) {
        this.fileExts = fileExts;
    }

    public String getValueList() {
        return valueList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }

    public String getLabelList() {
        return labelList;
    }

    public void setLabelList(String labelList) {
        this.labelList = labelList;
    }

    public FileStyle getFileStyle() {
        return fileStyle;
    }

    public void setFileStyle(FileStyle fileStyle) {
        this.fileStyle = fileStyle;
    }

    public String getOssBucket() {
        return ossBucket;
    }

    public void setOssBucket(String ossBucket) {
        this.ossBucket = ossBucket;
    }

    public String getInfoTip() {
        return infoTip;
    }

    public void setInfoTip(String infoTip) {
        this.infoTip = infoTip;
    }

    public String getInfoTipKey() {
        return infoTipKey;
    }

    public void setInfoTipKey(String infoTipKey) {
        this.infoTipKey = infoTipKey;
    }

}
