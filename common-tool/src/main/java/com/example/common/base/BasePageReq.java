package com.example.common.base;

import javax.validation.constraints.Min;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <要分页,实体继承此类即可>
 * 
 * @author  liunanhua
 * @version  [版本号, 2016年1月27日]
 */
public class BasePageReq extends BaseReq
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    @Min(1L)
    private int reqPageNum;
    
    @Min(5L)
    private int maxResults;
    
    public BasePageReq()
    {
        this.reqPageNum = 1;
        
        this.maxResults = 10;
    }
    
    public int getReqPageNum()
    {
        return this.reqPageNum;
    }
    
    public void setReqPageNum(int reqPageNum)
    {
        this.reqPageNum = reqPageNum;
    }
    
    public int getMaxResults()
    {
        return this.maxResults;
    }
    
    public void setMaxResults(int maxResults)
    {
        this.maxResults = maxResults;
    }
    
    @Override
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
}