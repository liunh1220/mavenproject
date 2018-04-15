package com.example.mybatis.base;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class QueryEvent<T>
{
    private T obj;
    
    private String statement;
    
    private Map<String, Object> parameter;
    
    private String appendSql;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public QueryEvent()
    {
        this.parameter = new HashMap();
    }
    
    public String getStatement()
    {
        return this.statement;
    }
    
    public void setStatement(String statement)
    {
        this.statement = statement;
    }
    
    public Map<String, Object> getParameter()
    {
        return this.parameter;
    }
    
    public void setParameter(Map<String, Object> parameter)
    {
        this.parameter = parameter;
    }
    
    public T getObj()
    {
        return this.obj;
    }
    
    public void setObj(T obj)
    {
        this.obj = obj;
    }
    
    public String getAppendSql()
    {
        return this.appendSql;
    }
    
    public void setAppendSql(String appendSql)
    {
        this.appendSql = appendSql;
    }
    
    private void converObjectToParameter(Object obj)
    {
        if (obj == null)
        {
            return;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        setMap(fields, obj);
    }
    
    private void setMap(Field[] fields, Object obj)
    {
        for (Field field : fields)
        {
            try
            {
                field.setAccessible(true);
                this.parameter.put(field.getName(), field.get(obj));
            }
            catch (ExceptionInInitializerError e)
            {
                this.parameter.put(field.getName(), null);
            }
            catch (NullPointerException e)
            {
                this.parameter.put(field.getName(), null);
            }
            catch (IllegalArgumentException e)
            {
                this.parameter.put(field.getName(), null);
            }
            catch (IllegalAccessException e)
            {
                this.parameter.put(field.getName(), null);
            }
        }
    }
    
    public void initParameter()
    {
        converObjectToParameter(this.obj);
        if ((null == this.appendSql) || ("".equals(this.appendSql)))
            return;
        this.parameter.put("appendSql", this.appendSql);
    }
    
    public void putParameter(String key, Object value)
    {
        this.parameter.put(key, value);
    }
    
    public void removeParameter(String key)
    {
        this.parameter.remove(key);
    }
}