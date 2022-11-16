package com.dodo.privilege.entity.admin_1.base_1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.field.DodoShowColumn;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.annotation.right.DodoRowRight;
import com.dodo.common.annotation.right.DodoRowRightGroup;
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
        nameKey = "dodo.privilege.admin.base.RoleGroup.entityKey",
        actions = { DodoAction.ALL },
        levelOne = @DodoMenu(nameKey = "dodo.privilege.admin.menuNameKey", sortSeq = 1),
        levelTwo = @DodoMenu(nameKey = "dodo.privilege.admin.base.menuNameKey", sortSeq = 1),
        levelThree = @DodoMenu(nameKey = "dodo.privilege.admin.base.RoleGroup.menuNameKey", sortSeq = 3))
@DodoRowRightGroup({ @DodoRowRight(entityProperty = "admin") })
public class RoleGroup extends BaseEntity {
    private static final long serialVersionUID = 341638853662381275L;

    @DodoField(nameKey = "dodo.privilege.admin.base.RoleGroup.namekey.admin", sortSeq = 0, isAdmin = true)
    private Admin             admin;

    @DodoShowColumn(sortSeq = 0)
    @DodoField(
            nameKey = "dodo.privilege.admin.base.RoleGroup.namekey.name",
            sortSeq = 1,
            queryOnList = true,
            isRemoteCheck = true)
    private String            name;

    @DodoField(
            nameKey = "dodo.privilege.admin.base.RoleGroup.namekey.description",
            sortSeq = 4,
            listable = false,
            isTextArea = true)
    private String            description;

    @DodoField(
            nameKey = "dodo.privilege.admin.base.RoleGroup.namekey.allRoles",
            sortSeq = 5,
            isPopup = true,
            isnullable = false,
            listable = false)
    private Set<Role>         allRoles;

    private Set<Admin>        adminSet         = new HashSet<Admin>();

    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy("sortSeq asc")
    public Set<Role> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(Set<Role> allRoles) {
        this.allRoles = allRoles;
    }

    @Column(nullable = false, length = 32)
    public String getName() {
        return this.name;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    @Column(length = 128)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String paramString) {
        this.description = paramString;
    }

    @ManyToMany(mappedBy = "roleGroupSet", fetch = FetchType.LAZY)
    public Set<Admin> getAdminSet() {
        return this.adminSet;
    }

    public void setAdminSet(Set<Admin> paramSet) {
        this.adminSet = paramSet;
    }

    @Transient
    public void onSave() {
        super.onSave();
    }

    @Transient
    public void onUpdate() {
        super.onUpdate();
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String toString() {
        return this.name;
    }

}
