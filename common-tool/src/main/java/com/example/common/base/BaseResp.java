package com.example.common.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.alibaba.fastjson.JSONObject;

@XmlRootElement(name = "Resp")
public class BaseResp
{
    private String code;
    
    private String description;
    
    public BaseResp()
    {
        this.code = "000001";
        
        this.description = "ERROR_UNKNOWN";
    }
    
    public void setCode(String code)
    {
        this.code = code;
        if (!("000000".equals(code)))
            return;
        setDescription("SUCCESS");
    }
    
    @XmlElement
    public String getCode()
    {
        return this.code;
    }
    
    @XmlElement
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
}