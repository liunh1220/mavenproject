package com.example.common.exception;

public class ServicesException extends Exception
{
    private static final long serialVersionUID = -6137067991428578374L;
    
    private String code;
    
    private String exceptionMsg;
    
    public ServicesException(String resultCode)
    {
        this.code = resultCode;
    }
    
    public ServicesException(String code, String exceptionMsg)
    {
        this.code = code;
        this.exceptionMsg = exceptionMsg;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public void setExceptionMsg(String exceptionMsg)
    {
        this.exceptionMsg = exceptionMsg;
    }
    
    public String getCode()
    {
        return this.code;
    }
    
    public String getExceptionMsg()
    {
        return this.exceptionMsg;
    }
}