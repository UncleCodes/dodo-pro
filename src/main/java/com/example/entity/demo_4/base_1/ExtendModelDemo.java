package com.example.entity.demo_4.base_1;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SortNatural;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.field.DodoViewGroup;
import com.dodo.common.annotation.menu.DodoMenu;
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
        name = "动态扩展(前往模型扩展模块动态扩展模型)",
        actions = { DodoAction.ALL },
        levelOne = @DodoMenu(name = "Demo系统", sortSeq = 7),
        levelTwo = @DodoMenu(name = "基础演示", sortSeq = 1),
        levelThree = @DodoMenu(name = "动态扩展", sortSeq = 13))
public class ExtendModelDemo extends BaseEntity {

    private static final long serialVersionUID = -1353659153582841947L;

    @DodoField(sortSeq = 1, name = "模型原有字段",maxLength=12)
    private String            field1;

    @DodoField(name = "扩展字段", sortSeq = 2)
    private Map<String, String> extendConfiger;

    @ElementCollection
    @MapKeyColumn(name = "extend_key", nullable = false, length = 30)
    @Column(name = "extend_value", nullable = false, length = 1024)
    @SortNatural
    public Map<String, String> getExtendConfiger() {
        return extendConfiger;
    }

    public void setExtendConfiger(Map<String, String> extendConfiger) {
        this.extendConfiger = extendConfiger;
    }
    
    @Column(length=12)
    public String getField1() {
        return field1;
    }
    
    public void setField1(String field1) {
        this.field1 = field1;
    }
}
