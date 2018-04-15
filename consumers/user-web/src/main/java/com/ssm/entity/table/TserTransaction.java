package com.ssm.entity.table;

import java.math.BigDecimal;
import java.util.Date;

public class TserTransaction {
    /**  */
    private String id;

    /**  */
    private String userId;

    /** ���׽�� */
    private BigDecimal amount;

    /** �������� */
    private Byte tranType;

    /** 1���ˣ�2���� */
    private Boolean inOunt;

    /**  */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getTranType() {
        return tranType;
    }

    public void setTranType(Byte tranType) {
        this.tranType = tranType;
    }

    public Boolean getInOunt() {
        return inOunt;
    }

    public void setInOunt(Boolean inOunt) {
        this.inOunt = inOunt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}