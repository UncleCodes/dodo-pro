package com.dodo.privilege.entity.admin_1.config_5;

import javax.persistence.Column;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.field.DodoShowColumn;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.annotation.right.DodoButtonRight;
import com.dodo.common.annotation.right.DodoButtonRightEvent;
import com.dodo.common.annotation.right.DodoButtonRightModel;
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
@javax.persistence.Entity
@DynamicInsert
@DodoEntity(
        nameKey = "dodo.privilege.admin.config.FormModel.entityKey",
        actions = { DodoAction.ALL },
        levelOne = @DodoMenu(nameKey = "dodo.privilege.admin.menuNameKey", sortSeq = 1),
        levelTwo = @DodoMenu(nameKey = "dodo.privilege.admin.config.menuNameKey", sortSeq = 5),
        levelThree = @DodoMenu(nameKey = "dodo.privilege.admin.config.FormModel.menuNameKey", sortSeq = 7))
@DodoButtonRight(
        nameKey = "dodo.privilege.admin.config.FormModel.button.vide_design.namekey",
        path = "${rootPath}/formmodeldesign/vide_design.jhtml",
        model = DodoButtonRightModel.ROW,
        event = DodoButtonRightEvent.URL)
public class FormModel extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -377970452191228204L;

    @DodoField(
            sortSeq = 1,
            nameKey = "dodo.privilege.admin.config.FormModel.namekey.modelName",
            isnullable = false,
            queryOnList = true,
            maxLength = 32)
    @DodoShowColumn(sortSeq = 1)
    private String            modelName;

    @DodoField(
            sortSeq = 2,
            nameKey = "dodo.privilege.admin.config.FormModel.namekey.isUse",
            isnullable = false,
            queryOnList = true)
    private Boolean           isUse;

    @Column(length = 32)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

    @Override
    public String toString() {
        return modelName;
    }

}
