package com.dodo.common.annotation.right;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解，表示一个单独的按钮权限<br/>
 * 
 * 
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Repeatable(DodoButtonRights.class)
public @interface DodoButtonRight {
    /**
     * 按钮的显示文本名称</br> 该配置和<strong>buttoKey</strong>属性只能二选一</br>
     * 
     * @see DodoButtonRight#buttoKey()
     * */
    public String name() default "";

    /**
     * 按钮的显示文本名称Key 该Key来源于Spring资源文件 messages*.properties中的Key</br>
     * 该配置和<strong>buttonName</strong>属性只能二选一</br>
     * 
     * @see DodoButtonRight#buttonName()
     * */
    public String nameKey() default "";

    /**
     * 访问模式 打开URL方式 、ajax方式 、弹窗编辑属性方式
     * 
     * @see DodoButtonRightEvent
     * */
    public DodoButtonRightEvent event();

    /**
     * <strong>【1】、当event = AJAX时，配置ajax访问地址，即：资源路径<br/>
     * </strong> 实际访问路径 = 后台根路径 + 模块路径 + path()<br/>
     * 配置示例：/cancel,/make/all,/test_make<br/>
     * <br/>
     * <strong>【2】、当event = URL时，配置需要打开的页面地址<br/>
     * </strong> 1、绝对路径，如https://www.bydodo.com<br/>
     * 2.1、相对于后台路径，如${rootPath}/xxx/xxx.jhtml<br/>
     * 2.1、相对于前台路径，如${webHomeUrl}/xxx/xxx.dhtml 或者 ${webHomeUrl}/xxx/xxx.htm<br/>
     * <strong>注：<br/>
     * ${rootPath} 代表配置文件中配置的后台管理总路径:dodo.backmanage.view.rootPath<br/>
     * ${webHomeUrl} 代表配置文件中配置的网站前台Url:dodo.common.config.web.homeurl<br/>
     * </strong>
     * */
    public String path();

    /**
     * <strong>当event = AJAX时启用，ajax提交前的提示信息<br/>
     * </strong>
     * */
    public String confirmMsg() default "";

    /**
     * <strong>当event = EDIT_PROPERTY时启用，需要编辑的字段名称<br/>
     * </strong>
     * */
    public String[] editPropertys() default {};

    /**
     * @see DodoButtonRightModel
     * */
    public DodoButtonRightModel model();

    /**
     * @see DodoButtonLocation
     * */
    public DodoButtonLocation location() default DodoButtonLocation.BOTTOM;

    /**
     * 排列顺序，表示按钮出现的先后顺序
     * */
    public int sortSeq() default 0;

    public String urlTarget() default "_self";

    /**
     * 显示条件 支持JS语法， 使用d代表行数据 行模式下启用
     * */
    public String displayCondition() default "";
}
