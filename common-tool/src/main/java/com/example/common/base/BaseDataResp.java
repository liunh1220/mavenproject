package com.example.common.base;

import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Resp")
public class BaseDataResp extends BaseResp
{
	
    public BaseDataResp(Object data) {
		super();
		this.data = data;
	}

	private Object data;
    
    @XmlElement
    public Object getData()
    {
        return this.data;
    }
    
    public void setData(Object data)
    {
        if (null != data)
        {
            this.data = data;
        }
        else
        {
            this.data = "";
        }
    }
    
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
}