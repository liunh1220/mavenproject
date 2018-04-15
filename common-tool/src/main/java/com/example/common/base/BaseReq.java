package com.example.common.base;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.alibaba.fastjson.JSONObject;
import com.example.common.enums.RequestSourceType;

public class BaseReq implements Serializable
{
    private static final long serialVersionUID = -6869536444111172516L;
    
    private String imei;
    
    @NotBlank
    private String opSource;
    
    private RequestSourceType reqSource;
    
    private String version;
    
    public BaseReq()
    {
        this.opSource = "PC";
        
        this.reqSource = RequestSourceType.FRONT;
        
        this.version = "0";
    }
    
    public RequestSourceType getReqSource()
    {
        return this.reqSource;
    }
    
    public void setReqSource(RequestSourceType reqSource)
    {
        this.reqSource = reqSource;
    }
    
    public String getImei()
    {
        return this.imei;
    }
    
    public void setImei(String imei)
    {
        this.imei = imei;
    }
    
    public String getOpSource()
    {
        return this.opSource;
    }
    
    public void setOpSource(String opSource)
    {
        this.opSource = opSource;
    }
    
    public String getVersion()
    {
        return this.version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
}
