package com.example.entity.demo_4.base_1;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.annotation.right.DodoButtonLocation;
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
@Entity
@DynamicInsert
@DodoEntity(
        name = "自定义按钮",
        actions = { DodoAction.ALL },
        levelOne = @DodoMenu(name = "Demo系统", sortSeq = 7),
        levelTwo = @DodoMenu(name = "基础演示", sortSeq = 1),
        levelThree = @DodoMenu(name = "自定义按钮演示", sortSeq = 7))
@DodoButtonRight(
        name = "模块AJAX",
        path = "/top/ajax",
        model = DodoButtonRightModel.MODEL,
        location = DodoButtonLocation.TOP,
        event = DodoButtonRightEvent.AJAX,
        confirmMsg = "后台设置的提示语？")
@DodoButtonRight(
        name = "完善",
        path = "/row/edit_check",
        model = DodoButtonRightModel.ROW,
        event = DodoButtonRightEvent.EDIT_PROPERTY,
        editPropertys = { "field1" })
@DodoButtonRight(
        name = "模块URL",
        path = "https://www.0yi0.com",
        model = DodoButtonRightModel.MODEL,
        location = DodoButtonLocation.TOP,
        event = DodoButtonRightEvent.URL)
@DodoButtonRight(
        name = "行AJAX",
        path = "/row/ajax",
        model = DodoButtonRightModel.ROW,
        event = DodoButtonRightEvent.AJAX,
        confirmMsg = "今天心情好吗？")
@DodoButtonRight(
        name = "后台URL",
        path = "${rootPath}/special/view.jhtml",
        model = DodoButtonRightModel.ROW,
        event = DodoButtonRightEvent.URL)
@DodoButtonRight(
        name = "前台URL",
        path = "${webHomeUrl}/test.html",
        model = DodoButtonRightModel.ROW,
        event = DodoButtonRightEvent.URL)
@DodoButtonRight(
        name = "站外URL",
        path = "https://www.0yi0.com",
        model = DodoButtonRightModel.ROW,
        event = DodoButtonRightEvent.URL)
@DodoButtonRight(
        name = "处理",
        path = "/row/deal_busi",
        model = DodoButtonRightModel.ROW,
        event = DodoButtonRightEvent.EDIT_PROPERTY,
        editPropertys = { "field1", "field2" })
public class ButtonRightDemo extends BaseEntity {

    private static final long serialVersionUID = 5475359959248427119L;

    @DodoField(sortSeq = 4, name = "字段1")
    private String            field1;

    @DodoField(sortSeq = 5, name = "字段2")
    private String            field2;

    @Column(length = 8)
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Column(length = 8)
    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Override
    public String entitySummary() {
        return field1 + " " + field2;
    }
}
