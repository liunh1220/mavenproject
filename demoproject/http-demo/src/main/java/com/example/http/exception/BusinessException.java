package com.example.http.exception;

/**
 * 业务层异常
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
