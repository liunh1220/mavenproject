package com.example.common.base;

/**
 * 参数.
 * 
 */
public abstract interface VariableBean
{
    
    /**
     * 获取参数类型ID.
     * 
     * @return {@link String}
     */
    public abstract String getType();
    
    /**
     * 获取参数关键字.
     * 
     * @return {@link String}
     */
    public abstract String getKey();
    
    /**
     * 获取参数值.
     * 
     * @return {@link String}
     */
    public abstract String getValue();
    
    /**
     * 获取参数描述.
     * 
     * @return {@link String}
     */
    public abstract String getDescription();
    
    /**
     * 是否需要初始化.
     * 
     * @return {@link boolean}
     */
    public abstract boolean isInit();
    
    /**
     * 获取参数类型ID.
     * 
     * @return {@link String}
     */
    public abstract String getTypeName();
    
    /**
     * 是否取数据库值
     * <功能详细描述>
     * @return true 是  false 否
     */
    public abstract boolean isDb();
}
