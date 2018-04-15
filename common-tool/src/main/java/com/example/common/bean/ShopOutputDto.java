package com.example.common.bean;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.example.common.util.DateUtil;

public class ShopOutputDto {

	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 分销商ID
	 */
	private String sellerId;
	/**
	 * 登录账号
	 */
	private String loginName;
	/**
	 * 店铺名称
	 */
	private String name;
	/**
	 * 店铺公告
	 */
	private String notice;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 店铺状态：1开启，2关闭
	 */
	private Integer status;
	/**
	 * 店铺URL
	 */
	private String shopUrl;
	/**
	 * 店铺Logo图片URL
	 */
	private String logoUrl;
	/**
	 * 店招图片URL
	 */
	private String signUrl;
	/**
	 * 二维码图片URL
	 */
	private String qrCodeUrl;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 最后更新人
	 */
	private String updateUser;
	/**
	 * 店铺跟踪码
	 */
	private String validateCode;
	/**
	 * 审核序号
	 */
	private Integer verifySeries;
	/**
	 * 店铺编码
	 */
	private String shopCode;
	
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public ShopOutputDto(){
	}

	public ShopOutputDto(
		String id
	){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getSellerId() {
		return this.sellerId == null ? null : this.sellerId.trim();
	}
	public void setLoginName(String value) {
		this.loginName = value;
	}
	
	public String getLoginName() {
		return this.loginName == null ? null : this.loginName.trim();
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name == null ? null : this.name.trim();
	}
	public void setNotice(String value) {
		this.notice = value;
	}
	
	public String getNotice() {
		return this.notice == null ? null : this.notice.trim();
	}
	public void setContact(String value) {
		this.contact = value;
	}
	
	public String getContact() {
		return this.contact == null ? null : this.contact.trim();
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getMobile() {
		return this.mobile == null ? null : this.mobile.trim();
	}
	public void setStatus(Integer value) {
		value = value == null ? 0 : value;
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status == null ? 0 : this.status;
	}
	public void setShopUrl(String value) {
		this.shopUrl = value;
	}
	
	public String getShopUrl() {
		return this.shopUrl == null ? null : this.shopUrl.trim();
	}
	public void setLogoUrl(String value) {
		this.logoUrl = value;
	}
	
	public String getLogoUrl() {
		return this.logoUrl == null ? null : this.logoUrl.trim();
	}
	public void setSignUrl(String value) {
		this.signUrl = value;
	}
	
	public String getSignUrl() {
		return this.signUrl == null ? null : this.signUrl.trim();
	}
	public void setQrCodeUrl(String value) {
		this.qrCodeUrl = value;
	}
	
	public String getQrCodeUrl() {
		return this.qrCodeUrl == null ? null : this.qrCodeUrl.trim();
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public String getStringCreateTime() {
		if(this.createTime == null){
		return null;
		}
		return DateUtil.DateToString(this.createTime, DateUtil.DEFAULT_FORMAT);
	}
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public String getStringUpdateTime() {
		if(this.updateTime == null){
		return null;
		}
		return DateUtil.DateToString(this.updateTime, DateUtil.DEFAULT_FORMAT);
	}
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser == null ? null : this.updateUser.trim();
	}
	public void setValidateCode(String value) {
		this.validateCode = value;
	}
	
	public String getValidateCode() {
		return this.validateCode == null ? null : this.validateCode.trim();
	}
	public void setVerifySeries(Integer value) {
		value = value == null ? 0 : value;
		this.verifySeries = value;
	}
	
	public Integer getVerifySeries() {
		return this.verifySeries == null ? 0 : this.verifySeries;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}