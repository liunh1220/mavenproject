package com.example.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.example.common.base.BaseDomain;

public class BaohongOrderDetail extends BaseDomain implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private String id;
    
    /**
     * 
     */
    private BaohongOrder baohongOrderId;
    
    /**
     * 产品sku
     */
    private String productSku;
    
    /**
     * 产品数量
     */
    private String opQuantity;
    
    /**
     * 交易价格
     */
    private String transationPrice;
    
    /**
     * 成交单价
     */
    private String dealPrice;
    
	/**
	 * 配货单价（用于配货单显示）
	 */
	private String invoicePrice;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    public String getId()
    {
        return id;
    }
        
    public void setId(String id)
    {
        this.id = id;
    }
        
    public BaohongOrder getBaohongOrderId() {
		return baohongOrderId;
	}

	public void setBaohongOrderId(BaohongOrder baohongOrderId) {
		this.baohongOrderId = baohongOrderId;
	}

	public String getProductSku()
    {
        return productSku;
    }
        
    public void setProductSku(String productSku)
    {
        this.productSku = productSku;
    }
        
    public String getOpQuantity()
    {
        return opQuantity;
    }
        
    public void setOpQuantity(String opQuantity)
    {
        this.opQuantity = opQuantity;
    }
        
    public String getTransationPrice()
    {
        return transationPrice;
    }
        
    public void setTransationPrice(String transationPrice)
    {
        this.transationPrice = transationPrice;
    }
        
    public String getDealPrice()
    {
        return dealPrice;
    }
        
    public void setDealPrice(String dealPrice)
    {
        this.dealPrice = dealPrice;
    }
    

	public String getInvoicePrice() {
		return invoicePrice;
	}

	public void setInvoicePrice(String invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
        
}
