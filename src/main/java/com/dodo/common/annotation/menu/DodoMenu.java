package com.dodo.common.annotation.menu;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解表示单一菜单配置
 * 
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DodoMenu {
    public String name() default "";

    public String nameKey() default "";

    public int sortSeq();
}
