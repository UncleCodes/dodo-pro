package com.dodo.privilege.entity.admin_1.data_4;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.framework.entity.BaseEntity;
import com.dodo.privilege.entity.admin_1.base_1.Admin;
import com.dodo.privilege.entity.admin_1.config_5.Entity;

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
        nameKey = "dodo.privilege.admin.data.AdvancedQueryPlan.entityKey",
        actions = { DodoAction.VIEW, DodoAction.EXPORT, DodoAction.CHART, DodoAction.DELETE },
        levelOne = @DodoMenu(nameKey = "dodo.privilege.admin.menuNameKey", sortSeq = 1),
        levelTwo = @DodoMenu(nameKey = "dodo.privilege.admin.data.menuNameKey", sortSeq = 4),
        levelThree = @DodoMenu(nameKey = "dodo.privilege.admin.data.AdvancedQueryPlan.menuNameKey", sortSeq = 7))
public class AdvancedQueryPlan extends BaseEntity {

    private static final long serialVersionUID = 2610237868406258337L;

    @DodoField(sortSeq = 1, nameKey = "dodo.privilege.admin.data.AdvancedQueryPlan.namekey.entity")
    private Entity            entity;

    @DodoField(sortSeq = 3, nameKey = "dodo.privilege.admin.data.AdvancedQueryPlan.namekey.planTitle")
    private String            planTitle;

    @DodoField(
            sortSeq = 5,
            nameKey = "dodo.privilege.admin.data.AdvancedQueryPlan.namekey.planDetail",
            isTextArea = true,
            listable = false)
    private String            planDetail;

    @DodoField(sortSeq = 7, nameKey = "dodo.privilege.admin.data.AdvancedQueryPlan.namekey.addBy")
    private Admin             addBy;

    @OneToOne
    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Column(length = 128)
    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    @Lob
    public String getPlanDetail() {
        return planDetail;
    }

    public void setPlanDetail(String planDetail) {
        this.planDetail = planDetail;
    }

    @OneToOne
    public Admin getAddBy() {
        return addBy;
    }

    public void setAddBy(Admin addBy) {
        this.addBy = addBy;
    }

}
