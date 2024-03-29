package com.dodo.privilege.entity.admin_1.data_4;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.annotation.right.DodoRowRight;
import com.dodo.common.annotation.right.DodoRowRightGroup;
import com.dodo.common.framework.entity.BaseEntity;
import com.dodo.privilege.entity.admin_1.base_1.Admin;
import com.dodo.privilege.entity.admin_1.config_5.Entity;
import com.dodo.privilege.enums.ConvertStatus;

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
        nameKey = "dodo.privilege.admin.data.VideoConvertTask.entityKey",
        actions = { DodoAction.VIEW, DodoAction.EXPORT, DodoAction.CHART, DodoAction.DELETE },
        levelOne = @DodoMenu(nameKey = "dodo.privilege.admin.menuNameKey", sortSeq = 1),
        levelTwo = @DodoMenu(nameKey = "dodo.privilege.admin.data.menuNameKey", sortSeq = 4),
        levelThree = @DodoMenu(nameKey = "dodo.privilege.admin.data.VideoConvertTask.menuNameKey", sortSeq = 6))
@DodoRowRightGroup({ @DodoRowRight(entityProperty = "admin") })
public class VideoConvertTask extends BaseEntity {
    private static final long serialVersionUID = 4545273587822222739L;

    @DodoField(sortSeq = -1, nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.admin")
    private Admin             admin;

    @DodoField(
            sortSeq = 0,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.convertStatus",
            queryOnList = true)
    private ConvertStatus     convertStatus;

    @DodoField(sortSeq = 1, nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.convertPercent")
    private String            convertPercent;

    @DodoField(
            sortSeq = 2,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.targetEntity",
            addable = false,
            editable = false)
    private Entity            targetEntity;

    @DodoField(
            sortSeq = 3,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.entityId",
            addable = false,
            editable = false)
    private String            entityId;

    @DodoField(
            sortSeq = 4,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.fieldName",
            addable = false,
            editable = false)
    private String            fieldName;

    @DodoField(
            sortSeq = 5,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.fieldShowName",
            addable = false,
            editable = false)
    private String            fieldShowName;

    @DodoField(
            sortSeq = 6,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.extFieldName",
            addable = false,
            editable = false)
    private String            extFieldName;

    @DodoField(
            sortSeq = 7,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.extFieldShowName",
            addable = false,
            editable = false)
    private String            extFieldShowName;

    @DodoField(
            sortSeq = 8,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.videoFileId",
            addable = false,
            editable = false)
    private String            videoFileId;

    @DodoField(
            sortSeq = 9,
            nameKey = "dodo.privilege.admin.data.VideoConvertTask.namekey.videoFileStr",
            addable = false,
            editable = false,
            isVideo = true)
    private String            videoFileStr;

    @Override
    public void onSave() {
        super.onSave();
        if (convertStatus == null) {
            convertStatus = ConvertStatus.NEW;
        }
        if (convertPercent == null) {
            convertPercent = "0.00%";
        }
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public Entity getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }

    @Column(length = 32)
    public String getEntityId() {
        return entityId;
    }

    @Column(length = 64)
    public String getFieldName() {
        return fieldName;
    }

    @Column(length = 64)
    public String getFieldShowName() {
        return fieldShowName;
    }

    @Lob
    public String getVideoFileStr() {
        return videoFileStr;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldShowName(String fieldShowName) {
        this.fieldShowName = fieldShowName;
    }

    public void setVideoFileStr(String videoFileStr) {
        this.videoFileStr = videoFileStr;
    }

    @Column(length = 64)
    public String getExtFieldName() {
        return extFieldName;
    }

    @Column(length = 64)
    public String getExtFieldShowName() {
        return extFieldShowName;
    }

    public void setExtFieldName(String extFieldName) {
        this.extFieldName = extFieldName;
    }

    public void setExtFieldShowName(String extFieldShowName) {
        this.extFieldShowName = extFieldShowName;
    }

    @Column(length = 3)
    @Convert(converter = ConvertStatus.Converter.class)
    public ConvertStatus getConvertStatus() {
        return convertStatus;
    }

    @Column(length = 7)
    public String getConvertPercent() {
        return convertPercent;
    }

    public void setConvertStatus(ConvertStatus convertStatus) {
        this.convertStatus = convertStatus;
    }

    public void setConvertPercent(String convertPercent) {
        this.convertPercent = convertPercent;
    }

    @Column(length = 64)
    public String getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(String videoFileId) {
        this.videoFileId = videoFileId;
    }
}
