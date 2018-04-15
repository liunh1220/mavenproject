package com.example.quartz.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 启用状态
 * @author Administrator
 *
 */
public enum EnabledStatus {
	
	NONE,
	
	LOCKED,
	
	ERROR,
	
	COMPLETE,
	
	PAUSED,
	
	NORMAL,
	
	DISABLE,
	ENABLE;
	
public String descr;
    
    public String getChineseName()
    {
        return this.descr;
    }
    
    public static final EnabledStatus parse(String value)
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
