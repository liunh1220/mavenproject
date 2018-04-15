package com.ssm.domain;

import java.io.Serializable;

import java.util.Date;

import com.ssm.base.BaseDomain;

public class EmailConfig extends BaseDomain implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 6890334587187133629L;

	/**
     * 
     */
    private String id;
    
    /**
     * 类型名称
     */
    private String typeName;
    
    /**
     * 类型
     */
    private String typeId;
    
    /**
     * 店铺名称
     */
    private String sellerName;
    
    /**
     * 店铺id
     */
    private String sellerId;
    
    /**
     * 收件人
     */
    private String toEmail;
    
    /**
     * 类型名称
     */
    private String ccEmail;
    
    /**
     * 标示
     */
    private String flag;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date updateTime;
    
    public String getId()
    {
        return id;
    }
        
    public void setId(String id)
    {
        this.id = id;
    }
        
    public String getTypeName()
    {
        return typeName;
    }
        
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
        
    public String getTypeId()
    {
        return typeId;
    }
        
    public void setTypeId(String typeId)
    {
        this.typeId = typeId;
    }
        
    public String getSellerName()
    {
        return sellerName;
    }
        
    public void setSellerName(String sellerName)
    {
        this.sellerName = sellerName;
    }
        
    public String getSellerId()
    {
        return sellerId;
    }
        
    public void setSellerId(String sellerId)
    {
        this.sellerId = sellerId;
    }
        
    public String getToEmail()
    {
        return toEmail;
    }
        
    public void setToEmail(String toEmail)
    {
        this.toEmail = toEmail;
    }
        
    public String getCcEmail()
    {
        return ccEmail;
    }
        
    public void setCcEmail(String ccEmail)
    {
        this.ccEmail = ccEmail;
    }
        
    public String getFlag()
    {
        return flag;
    }
        
    public void setFlag(String flag)
    {
        this.flag = flag;
    }
        
    public Date getCreateTime()
    {
        return createTime;
    }
        
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
        
    public Date getUpdateTime()
    {
        return updateTime;
    }
        
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
        
}
