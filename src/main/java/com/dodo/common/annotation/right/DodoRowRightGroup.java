package com.dodo.common.annotation.right;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解，表示该实体的数据行级别的访问权限分组<br/>
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
@Repeatable(DodoRowRights.class)
public @interface DodoRowRightGroup {

    /**
     * 条件间的关联类型，AND=并且，OR=或者
     * */
    public MapType mapType() default MapType.AND;

    /**
     * 多个查询条件
     * */
    public DodoRowRight[] value();
}
