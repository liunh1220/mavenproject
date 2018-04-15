package com.example.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.common.base.BaseDomain;

public class BaohongOrder extends BaseDomain implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * ID
     */
    private String id;
    
    /**
     * 订单模型0:备货模式1:集货模式
     */
    private String orderModel;
    
    /**
     * 交货仓库
     */
    private String wareHouseCode;
    
    /**
     * 目的仓库
     */
    private String toWareHouseCode;
    
    /**
     * 收件人国家
     */
    private String oabCounty;
    
    /**
     * 收件人省份
     */
    private String oabStateName;
    
    /**
     * 收件人城市
     */
    private String oabCity;
    
	/**收件人县（区）
	 * 
	 */
	private String oabDistrict;
    
    /**
     * 运输方式代码
     */
    private String smCode;
    
    /**
     * 交易订单号
     */
    private String referenceNo;
    
    /**
     * 
     */
    private String trackingNumber;
    
    /**
     * 收件人姓名
     */
    private String oabName;
    
    /**
     * 收件人公司名
     */
    private String oabCompany;
    
    /**
     * 收件人邮编
     */
    private String oabPostcode;
    
    /**
     * 收件人地址1
     */
    private String oabStreetAddress1;
    
    /**
     * 收件人地址2
     */
    private String oabStreetAddress2;
    
    /**
     * 收件人电话
     */
    private String oabPhone;
    
    /**
     * 电子邮件
     */
    private String oabEmail;
    
    /**
     * 毛重 KG
     */
    private String grossWt;
    
    /**
     * 币种
     */
    private String currencyCode;
    
    /**
     * 证件类型 1:身份证 2:军官证 3:护照 4:其TA
     */
    private String idType;
    
    /**
     * 证件号码
     */
    private String idNumber;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 支付单号，前海仓必填
     */
    private String payNo;
    
    /**
     * 提交订单状态
     */
    private String orderStatus;
    
    /**
     * 折扣金额
     */
    private String discountRate;
    
    /**
     * 运费
     */
    private String deliveryFee;
    
    /**
     * 订单总额
     */
    private String amount;
    
    /**
     * 折扣金额
     */
    private String couponRate;
    
    /**
     * 发票模版编号
     */
    private String invoiceCode;
    
    /**
     * 原始交易订单号
     */
    private String transactionOrderCode;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 同步状态：0:未同步;1:已同步;2:同步成功;3:同步失败
     */
    private String syncStatus;
    
    /**
     * 同步时间
     */
    private Date syncTime;
    
    /**
     * 同步备注
     */
    private String syncRemark;
    
    private List<BaohongOrderDetail> detailsList;
    
    public String getId()
    {
        return id;
    }
        
    public void setId(String id)
    {
        this.id = id;
    }
        
    public String getOrderModel()
    {
        return orderModel;
    }
        
    public void setOrderModel(String orderModel)
    {
        this.orderModel = orderModel;
    }
        
    public String getWareHouseCode()
    {
        return wareHouseCode;
    }
        
    public void setWareHouseCode(String wareHouseCode)
    {
        this.wareHouseCode = wareHouseCode;
    }
        
    public String getToWareHouseCode()
    {
        return toWareHouseCode;
    }
        
    public void setToWareHouseCode(String toWareHouseCode)
    {
        this.toWareHouseCode = toWareHouseCode;
    }
        
    public String getOabCounty()
    {
        return oabCounty;
    }
        
    public void setOabCounty(String oabCounty)
    {
        this.oabCounty = oabCounty;
    }
        
    public String getOabStateName()
    {
        return oabStateName;
    }
        
    public void setOabStateName(String oabStateName)
    {
        this.oabStateName = oabStateName;
    }
        
    public String getOabCity()
    {
        return oabCity;
    }
        
    public void setOabCity(String oabCity)
    {
        this.oabCity = oabCity;
    }
        
    public String getOabDistrict() {
		return oabDistrict;
	}

	public void setOabDistrict(String oabDistrict) {
		this.oabDistrict = oabDistrict;
	}

	public String getSmCode()
    {
        return smCode;
    }
        
    public void setSmCode(String smCode)
    {
        this.smCode = smCode;
    }
        
    public String getReferenceNo()
    {
        return referenceNo;
    }
        
    public void setReferenceNo(String referenceNo)
    {
        this.referenceNo = referenceNo;
    }
        
    public String getTrackingNumber()
    {
        return trackingNumber;
    }
        
    public void setTrackingNumber(String trackingNumber)
    {
        this.trackingNumber = trackingNumber;
    }
        
    public String getOabName()
    {
        return oabName;
    }
        
    public void setOabName(String oabName)
    {
        this.oabName = oabName;
    }
        
    public String getOabCompany()
    {
        return oabCompany;
    }
        
    public void setOabCompany(String oabCompany)
    {
        this.oabCompany = oabCompany;
    }
        
    public String getOabPostcode()
    {
        return oabPostcode;
    }
        
    public void setOabPostcode(String oabPostcode)
    {
        this.oabPostcode = oabPostcode;
    }
        
    public String getOabStreetAddress1()
    {
        return oabStreetAddress1;
    }
        
    public void setOabStreetAddress1(String oabStreetAddress1)
    {
        this.oabStreetAddress1 = oabStreetAddress1;
    }
        
    public String getOabStreetAddress2()
    {
        return oabStreetAddress2;
    }
        
    public void setOabStreetAddress2(String oabStreetAddress2)
    {
        this.oabStreetAddress2 = oabStreetAddress2;
    }
        
    public String getOabPhone()
    {
        return oabPhone;
    }
        
    public void setOabPhone(String oabPhone)
    {
        this.oabPhone = oabPhone;
    }
        
    public String getOabEmail()
    {
        return oabEmail;
    }
        
    public void setOabEmail(String oabEmail)
    {
        this.oabEmail = oabEmail;
    }
        
    public String getGrossWt()
    {
        return grossWt;
    }
        
    public void setGrossWt(String grossWt)
    {
        this.grossWt = grossWt;
    }
        
    public String getCurrencyCode()
    {
        return currencyCode;
    }
        
    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }
        
    public String getIdType()
    {
        return idType;
    }
        
    public void setIdType(String idType)
    {
        this.idType = idType;
    }
        
    public String getIdNumber()
    {
        return idNumber;
    }
        
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }
        
    public String getRemark()
    {
        return remark;
    }
        
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
        
    public String getPayNo()
    {
        return payNo;
    }
        
    public void setPayNo(String payNo)
    {
        this.payNo = payNo;
    }
        
    public String getOrderStatus()
    {
        return orderStatus;
    }
        
    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }
        
    public String getDiscountRate()
    {
        return discountRate;
    }
        
    public void setDiscountRate(String discountRate)
    {
        this.discountRate = discountRate;
    }
        
    public String getDeliveryFee()
    {
        return deliveryFee;
    }
        
    public void setDeliveryFee(String deliveryFee)
    {
        this.deliveryFee = deliveryFee;
    }
        
    public String getAmount()
    {
        return amount;
    }
        
    public void setAmount(String amount)
    {
        this.amount = amount;
    }
        
    public String getCouponRate()
    {
        return couponRate;
    }
        
    public void setCouponRate(String couponRate)
    {
        this.couponRate = couponRate;
    }
        
    public String getInvoiceCode()
    {
        return invoiceCode;
    }
        
    public void setInvoiceCode(String invoiceCode)
    {
        this.invoiceCode = invoiceCode;
    }
        
    public String getTransactionOrderCode()
    {
        return transactionOrderCode;
    }
        
    public void setTransactionOrderCode(String transactionOrderCode)
    {
        this.transactionOrderCode = transactionOrderCode;
    }

	public List<BaohongOrderDetail> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<BaohongOrderDetail> detailsList) {
		this.detailsList = detailsList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}

	public Date getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	public String getSyncRemark() {
		return syncRemark;
	}

	public void setSyncRemark(String syncRemark) {
		this.syncRemark = syncRemark;
	}
        
}
