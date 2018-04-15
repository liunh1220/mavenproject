package com.ssm.domain;

import java.io.Serializable;

import com.ssm.base.BaseDomain;

public class ConsignSyncLog extends BaseDomain implements Serializable{

	private static final long serialVersionUID = -710351566077577327L;

	/**
	 * 同步时间
	 */
    private Long syncTime;

    /**
     * 同步类型，IN:从B2C同步至接口平台；OUT:从接口平台同步至淘宝接口；CM:接口平台发送发货指令
     */
    private String syncType;

    /**
     * 同步状态，0:同步未完成;1:同步已完成;
     */
    private String syncStatus;
    
    /**
     * 同步批次号,格式为:yyyyMMddHHmmss
     */
    private String syncBatchNo;

    /**
     * 同步总纪录数
     */
	private Integer totalNum;

	/**
	 * 成功纪录数
	 */
    private Integer successNum;

    /**
     * 失败纪录数
     */
    private Integer failureNum;

    /**
     * 同步失败明细信息，
		保存同步失败订单的seller_tag（淘宝卖家id）
		及tid（交易编号）
		和同步失败原因
     */
    private String failureDetail;

    public Long getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Long syncTime) {
        this.syncTime = syncTime;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType == null ? null : syncType.trim();
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus == null ? null : syncStatus.trim();
    }
    
    public String getSyncBatchNo() {
		return syncBatchNo;
	}

	public void setSyncBatchNo(String syncBatchNo) {
		this.syncBatchNo = syncBatchNo;
	}

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(Integer failureNum) {
        this.failureNum = failureNum;
    }

    public String getFailureDetail() {
        return failureDetail;
    }

    public void setFailureDetail(String failureDetail) {
        this.failureDetail = failureDetail == null ? null : failureDetail.trim();
    }
}