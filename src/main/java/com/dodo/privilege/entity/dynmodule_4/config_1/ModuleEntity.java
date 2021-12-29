package com.dodo.privilege.entity.dynmodule_4.config_1;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.field.DodoShowColumn;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.annotation.right.DodoButtonLocation;
import com.dodo.common.annotation.right.DodoButtonRight;
import com.dodo.common.annotation.right.DodoButtonRightEvent;
import com.dodo.common.annotation.right.DodoButtonRightModel;
import com.dodo.common.framework.entity.BaseEntity;
import com.dodo.privilege.entity.admin_1.config_5.MenuInfo;

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
        nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.entityKey",
        actions = { DodoAction.UPDATE, DodoAction.DELETE, DodoAction.CHART, DodoAction.VIEW, DodoAction.EXPORT },
        levelOne = @DodoMenu(nameKey = "dodo.privilege.dynmodule.menuNameKey", sortSeq = 4),
        levelTwo = @DodoMenu(nameKey = "dodo.privilege.dynmodule.config.menuNameKey", sortSeq = 1),
        levelThree = @DodoMenu(nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.menuNameKey", sortSeq = 1))
@DodoButtonRight(
        nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.button.design.namekey",
        path = "${rootPath}/dynamicmodule/design.jhtml",
        model = DodoButtonRightModel.ROW,
        event = DodoButtonRightEvent.URL)
@DodoButtonRight(
        nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.button.viewreport.namekey",
        path = "${rootPath}/dynamicmodule/view.jhtml",
        model = DodoButtonRightModel.ROW,
        urlTarget = "_blank",
        event = DodoButtonRightEvent.URL)
@DodoButtonRight(
        nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.button.design.namekey",
        path = "${rootPath}/dynamicmodule/design.jhtml",
        model = DodoButtonRightModel.MODEL,
        event = DodoButtonRightEvent.URL,
        urlTarget = "_blank",
        location = DodoButtonLocation.TOP)
public class ModuleEntity extends BaseEntity {
    private static final long  serialVersionUID = -2026205118666904848L;

    @DodoField(
            sortSeq = 1,
            nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.namekey.menu",
            isnullable = false,
            editable = false,
            queryParams = "ne(\"menuLevel\",com.dodo.common.annotation.menu.DodoMenuLevel.LEVEL3)")
    private MenuInfo           menu;

    @DodoField(
            sortSeq = 2,
            nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.namekey.name",
            isnullable = false,
            maxLength = 64,
            queryOnList = true,
            editable = false)
    @DodoShowColumn(sortSeq = 0)
    private String             name;

    @DodoField(
            sortSeq = 3,
            nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.namekey.execSql",
            isnullable = false,
            isTextArea = true,
            listable = false,
            editable = false)
    private String             execSql;

    @DodoField(
            sortSeq = 4,
            nameKey = "dodo.privilege.dynmodule.config.ModuleEntity.namekey.moduleFields",
            editable = false,
            addable = false,
            listable = false)
    private List<ModuleField>  moduleFields;

    @DodoField(
            sortSeq = 4,
            nameKey = "dodo.privilege.report.config.ModuleEntity.namekey.moduleButtons",
            editable = false,
            addable = false,
            listable = false)
    private List<ModuleButton> moduleButtons;

    @Column(length = 64)
    public String getName() {
        return name;
    }

    @Lob
    public String getExecSql() {
        return execSql;
    }

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "moduleEntity")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("sortSeq asc")
    public List<ModuleButton> getModuleButtons() {
        return moduleButtons;
    }

    public void setModuleButtons(List<ModuleButton> moduleButtons) {
        this.moduleButtons = moduleButtons;
    }

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "moduleEntity")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("sortSeq asc")
    public List<ModuleField> getModuleFields() {
        return moduleFields;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExecSql(String execSql) {
        this.execSql = execSql;
    }

    public void setModuleFields(List<ModuleField> moduleFields) {
        this.moduleFields = moduleFields;
    }

    @OneToOne
    public MenuInfo getMenu() {
        return menu;
    }

    public void setMenu(MenuInfo menu) {
        this.menu = menu;
    }
}
