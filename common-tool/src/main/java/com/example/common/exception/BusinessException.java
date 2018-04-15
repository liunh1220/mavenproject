package com.example.common.exception;

/**
 * 业务层异常
 * @author xiongzy
 *
 */
public class BusinessException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4896730093326076979L;

	/**
     * 
     * @param msg 
     */
    public BusinessException(String msg) {
        super(msg);
    }
    
    /**
     * 
     * @param msg 
     * @param t 
     */
    public BusinessException(String msg, Throwable t) {
        super(msg, t);
        
        this.setStackTrace(t.getStackTrace());
    }
}
