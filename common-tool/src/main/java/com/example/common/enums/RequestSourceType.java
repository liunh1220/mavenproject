package com.example.common.enums;

import org.apache.commons.lang.StringUtils;

public enum RequestSourceType
{
    FRONT, CONSOLE, IOS, AD, WX;
    
    public String descr;
    
    public String getChineseName()
    {
        return this.descr;
    }
    
    public static final RequestSourceType parse(String value)
    {
        if (StringUtils.isEmpty(value))
        {
            return null;
        }
        try
        {
            return valueOf(value);
        }
        catch (Throwable t)
        {
        }
        return null;
    }
    
    public String getDescr()
    {
        return this.descr;
    }
    
    public void setDescr(String descr)
    {
        this.descr = descr;
    }
}
