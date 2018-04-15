package com.example.quartz.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 任务状态
 * 
 * @author 
 *
 */
public enum JobStatus {
	
	/**
	 * 待执行
	 */
	waiting, 
	
	/**
	 * 运行中
	 */
	runing, 
	
	/**
	 * 暂停
	 */
	pause;
	
   public String descr;
    
    public String getChineseName()
    {
        return this.descr;
    }
    
    public static final JobStatus parse(String value)
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
