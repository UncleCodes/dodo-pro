package com.dodo.common.annotation.cascade;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用来配置生成无限级联动下拉列表，如关系：国家->省份->城市->区域->街道
 * 
 * <p>Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
@Inherited 
public @interface DodoCascade {
	/**
	 * 级联分组
	 * */
	public int group() default 0;
	
	/**
	 * 级联上级
	 * */
	public String parentField() default "";
	/**
	 * 附加查询条件，多个查询条件使用符号“;”隔开 查询条件为与关系
	 * <br/>
	 * <br/>
	 * 如：eq("userName", "zhangsan");gt("score",1000);like("city","%中国%");<br/>
	 * 所有支持列表如下<br/>
	 * 1、eq(String propertyName,Object value)<b>[propertyName 值等于 value]</b><br/>
	 * 2、ne(String propertyName,Object value)<b>[propertyName 值不等于 value]</b><br/>
	 * 3、ge(String propertyName,Object value)<b>[propertyName 值大于等于 value]</b><br/>
	 * 4、gt(String propertyName,Object value)<b>[propertyName 值大于 value]</b><br/>
	 * 5、lt(String propertyName,Object value)<b>[propertyName 值小于 value]</b><br/>
	 * 6、le(String propertyName,Object value)<b>[propertyName 值小于等于 value]</b><br/>
	 * 7、in(String propertyName,Object... values)<b>[propertyName 值在 values 中存在]</b><br/>
	 * 8、notIn(String propertyName,Object... values)<b>[propertyName 值在 values 中不存在]</b><br/>
	 * 9、eqProperty(String propertyName,String otherPropertyName)<b>[propertyName 值等于 otherPropertyName 的值]</b><br/>
	 * 10、neProperty(String propertyName,String otherPropertyName)<b>[propertyName 值不等于 otherPropertyName 的值]</b><br/>
	 * 11、geProperty(String propertyName,String otherPropertyName)<b>[propertyName 值大于等于 otherPropertyName 的值]</b><br/>
	 * 12、gtProperty(String propertyName,String otherPropertyName)<b>[propertyName 值大于 otherPropertyName 的值]</b><br/>
	 * 13、ltProperty(String propertyName,String otherPropertyName)<b>[propertyName 值小于 otherPropertyName 的值]</b><br/>
	 * 14、leProperty(String propertyName,String otherPropertyName)<b>[propertyName 值小于等于 otherPropertyName 的值]</b><br/>
	 * 15、like(String propertyName,String value)<b>[propertyName 值模糊匹配 value、区分大小写]</b><br/>
	 * 16、notLike(String propertyName,String value)<b>[propertyName 值模糊不匹配 value、区分大小写]</b><br/>
	 * 17、ilike(String propertyName,String value)<b>[propertyName 值模糊匹配 value、不区分大小写]</b><br/>	 
	 * 18、notiLike(String propertyName,String value)<b>[propertyName 值模糊不匹配 value、不区分大小写]</b><br/>	 
	 * 19、isEmpty(String propertyName)<b>[propertyName 为空，propertyName必须为集合类型]</b><br/>
	 * 20、isNotEmpty(String propertyName)<b>[propertyName 不为空，propertyName必须为集合类型]</b><br/>
	 * 21、isNotNull(String propertyName)<b>[propertyName 不为Null，propertyName不能为集合类型]</b><br/>
	 * 22、isNull(String propertyName)<b>[propertyName 为Null，propertyName不能为集合类型]</b><br/>
	 * 23、sizeEq(String propertyName, Integer size)<b>[propertyName 元素个数等于 size，propertyName必须为集合类型]</b><br/>
	 * 24、sizeGe(String propertyName, Integer size)<b>[propertyName 元素个数大于等于 size，propertyName必须为集合类型]</b><br/>
	 * 25、sizeGt(String propertyName, Integer size)<b>[propertyName 元素个数大于 size，propertyName必须为集合类型]</b><br/>
	 * 26、sizeLe(String propertyName, Integer size)<b>[propertyName 元素个数小于等于 size，propertyName必须为集合类型]</b><br/>
	 * 27、sizeLt(String propertyName, Integer size)<b>[propertyName 元素个数小于 size，propertyName必须为集合类型]</b><br/>
	 * 28、sizeNe(String propertyName, Integer size)<b>[propertyName 元素个数不等于 size，propertyName必须为集合类型]</b><br/>
	 * 29、lengthEq(propertyName, Integer length)<b>[propertyName 长度等于 length，propertyName必须为String类型]</b><br/>
	 * 30、lengthGe(propertyName, Integer length)<b>[propertyName 长度大于等于 length，propertyName必须为String类型]</b><br/>
	 * 31、lengthGt(propertyName, Integer length)<b>[propertyName 长度大于 length，propertyName必须为String类型]</b><br/>
	 * 32、lengthLe(propertyName, Integer length)<b>[propertyName 长度小于等于 length，propertyName必须为String类型]</b><br/>
	 * 33、lengthLt(propertyName, Integer length)<b>[propertyName 长度小于 length，propertyName必须为String类型]</b><br/>
	 * 34、lengthNe(propertyName, Integer length)<b>[propertyName 长度不等于 length，propertyName必须为String类型]</b><br/>	 
	 * 35、between(String propertyName,Object value1,Object value2)<b>[propertyName 值介于 value1 和 value2 之间]</b><br/>
	 * 36、notBetween(String propertyName,Object value1,Object value2)<b>[propertyName 值不介于 value1 和 value2 之间]</b><br/>
	 * 37、lengthEqProperty(String propertyName,String otherPropertyName)<b>[propertyName 得长度 等于 otherPropertyName的长度，propertyName必须为String类型]</b><br/>
	 * 38、lengthGeProperty(String propertyName,String otherPropertyName)<b>[propertyName 得长度 大于等于 otherPropertyName的长度，propertyName必须为String类型]</b><br/>
	 * 39、lengthGtProperty(String propertyName,String otherPropertyName)<b>[propertyName 得长度 大于 otherPropertyName的长度，propertyName必须为String类型]</b><br/>
	 * 40、lengthLeProperty(String propertyName,String otherPropertyName)<b>[propertyName 得长度 小于等于 otherPropertyName的长度，propertyName必须为String类型]</b><br/>
	 * 41、lengthLtProperty(String propertyName,String otherPropertyName)<b>[propertyName 得长度 小于 otherPropertyName的长度，propertyName必须为String类型]</b><br/>
	 * 42、lengthNeProperty(String propertyName,String otherPropertyName)<b>[propertyName 得长度 不等于 otherPropertyName的长度，propertyName必须为String类型]</b><br/>
	 */
	public String queryParams() default "";
}
