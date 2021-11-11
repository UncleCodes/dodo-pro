package com.dodo.common.dynamicmodule;

/**
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
public class DynamicModuleException extends RuntimeException {

    private static final long serialVersionUID = -1977653008397605140L;

    public DynamicModuleException() {
        super();
    }

    public DynamicModuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DynamicModuleException(String message) {
        super(message);
    }

    public DynamicModuleException(Throwable cause) {
        super(cause);
    }
}
