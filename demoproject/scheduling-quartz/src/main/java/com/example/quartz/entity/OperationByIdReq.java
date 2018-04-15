package com.example.quartz.entity;

import javax.validation.constraints.NotBlank;

import com.example.common.base.BaseReq;

public class OperationByIdReq extends BaseReq
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7311799028663816797L;
    
    /**
     * id
     */
    @NotBlank
    private String id;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
}
