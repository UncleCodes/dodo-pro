package com.dodo.common.framework.service;

import javax.servlet.http.HttpServletRequest;

import com.dodo.common.dynamicmodule.DynamicModuleDesignBean;

/**
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
public interface DynamicModuleService {
    public void saveOrUpdateDesign(DynamicModuleDesignBean designBean, HttpServletRequest request);
}
