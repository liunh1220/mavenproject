package com.example.http.model;

import java.io.Serializable;
import java.util.Date;

public class Shop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2850670890929125929L;

	/**
	 * 唯一标识
	 */
	private String id;

	/**
	 * 加盟商名称
	 */
	private String name;

	/**
	 * 优购分配给加盟商应用的AppId
	 */
	private String appId;

	/**
	 * 优购分配给加盟商应用的AppKey
	 */
	private String appKey;

	/**
	 * 优购分配给加盟商应用的密钥
	 */
	private String appSession;

	/**
	 * 订单同步时间
	 */
	private String orderSyncTime;

	/**
	 * 库存同步时间
	 */
	private String storeSyncTime;

	// 订单退换货同步时间
	private String reSyncTime;

	/**
	 * 纪录创建时间
	 */
	private Date createTime;

	/**
	 * 纪录修改时间
	 */
	private Date updateTime;

	/**
	 * 是否启用同步
	 */
	private String enabledFlag;

	/**
	 * 是否拆单 Y:是 N：否 IS_SPLIT_ORDER
	 */
	private String isSplitOrder;

	/**
	 * 系统,当当：“DANGDANG”，西街：“XIJIE” SYSTEM
	 */
	private String system;

	/**
	 * SKU同步时间
	 */
	private String skuSyncTime;

	/**
	 * 店铺订单来源编号
	 */
	private String orderSourceNo;

	/**
	 * QQ账号
	 * 
	 * @return
	 */
	private String qqId;

	/**
	 * 订单监控时间,单位:分钟,不监控设置为0或空
	 */
	private Integer monitorTime;

	/**
	 * 仓库编码
	 */
	private String warehouseCode;

	/**
	 * 失效时间
	 */
	private Date appOutTime;

	/**
	 * 京东工单同步时间
	 */
	private Date workOrderSyncTime;

	/**
	 * 是否同步库存  Y:是 N：否
	 */
	private String storeSyncFlag;

	/**
	 * 企业编码
	 */
	private String enterpriseCode;

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getOrderSourceNo() {
		return orderSourceNo;
	}

	public void setOrderSourceNo(String orderSourceNo) {
		this.orderSourceNo = orderSourceNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSession() {
		return appSession;
	}

	public void setAppSession(String appSession) {
		this.appSession = appSession;
	}

	public String getOrderSyncTime() {
		return orderSyncTime;
	}

	public void setOrderSyncTime(String orderSyncTime) {
		this.orderSyncTime = orderSyncTime;
	}

	public String getStoreSyncTime() {
		return storeSyncTime;
	}

	public void setStoreSyncTime(String storeSyncTime) {
		this.storeSyncTime = storeSyncTime;
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

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getIsSplitOrder() {
		return isSplitOrder;
	}

	public void setIsSplitOrder(String isSplitOrder) {
		this.isSplitOrder = isSplitOrder;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getReSyncTime() {
		return reSyncTime;
	}

	public void setReSyncTime(String reSyncTime) {
		this.reSyncTime = reSyncTime;
	}

	public String getSkuSyncTime() {
		return skuSyncTime;
	}

	public void setSkuSyncTime(String skuSyncTime) {
		this.skuSyncTime = skuSyncTime;
	}

	public Shop() {

	}

	public Shop(String system) {
		super();
		this.system = system;
	}

	public Integer getMonitorTime() {
		return monitorTime;
	}

	public void setMonitorTime(Integer monitorTime) {
		this.monitorTime = monitorTime;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public Date getAppOutTime() {
		return appOutTime;
	}

	public void setAppOutTime(Date appOutTime) {
		this.appOutTime = appOutTime;
	}

	public Date getWorkOrderSyncTime() {
		return workOrderSyncTime;
	}

	public void setWorkOrderSyncTime(Date workOrderSyncTime) {
		this.workOrderSyncTime = workOrderSyncTime;
	}

	public String getStoreSyncFlag() {
		return storeSyncFlag;
	}

	public void setStoreSyncFlag(String storeSyncFlag) {
		this.storeSyncFlag = storeSyncFlag;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

}
