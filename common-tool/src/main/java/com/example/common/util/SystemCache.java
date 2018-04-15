package com.example.common.util;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

import com.example.common.base.VariableBean;

/**
 * 系统常量缓存
 * 
 * @author   
 * @version  [版本号, 2015年9月17日]
 */
public class SystemCache
{
    protected static Cache systemCache;
    
    /** 获取指定的枚举
     * <功能详细描述>
     * @param type
     * @return
     */
    public static String getProperty(VariableBean variableBean)
    {
        //常量值
        String value = null;
        //如果对象为空，返回NULL
        if (variableBean == null)
        {
            return value;
        }
        
        //如果键值为空，则返回NULL
        if (StringUtil.isEmpty(variableBean.getKey()))
        {
            return value;
        }
        
        //获取缓存中的常量值
        ValueWrapper cacheElement = systemCache.get(variableBean.getKey());
        
        if (cacheElement != null)
        {
            value = String.valueOf((Object)cacheElement);
        }
        
        //如果常量值为空，则取默认的值
        if (StringUtil.isEmpty(value))
        {
            value = variableBean.getValue();
        }
        return value;
    }
    
    /**
     * @return 返回 systemCache
     */
    public static Cache getSystemCache()
    {
        return systemCache;
    }
    
    /**
     * @param 对systemCache进行赋值
     */
    public static void setSystemCache(Cache systemCache)
    {
        SystemCache.systemCache = systemCache;
    }
    public static Object getCache(String key){
    	ValueWrapper element = systemCache.get(key);
        if(element==null)return null;
        return element;
    }
}

