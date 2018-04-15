package com.example.common.bean;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SkuBannerOutputDto extends OutputDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**  */
    private String id;

    /** 图片名称  */
    private String bannerName;

    /** 广告位类型，SKU:单品页广告 */
    private String type;

    /** 跳转类型：1、商品详情页，2、文章详情，3、品牌，4、分类、5、活动，6、专题，7、秒杀集合页，8、手动输入 */
    private Byte linkType;

    /** 链接内容名称  */
    private String linkName;

    /** 链接地址 */
    private String linkUrl;

    /** 广告位生效起始时间 */
    private Date timeStart;

    /** 广告位生效结束时间 */
    private Date timeEnd;

    /** 广告位图片地址  */
    private String picUrl;

    /** 创建时间  */
    private Date createTime;

    /** 更新时间  */
    private Date updateTime;

    /** 更新人 */
    private String updateUser;

	/**
	 * 广告状态
	 */
	private String status;
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName == null ? null : bannerName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Byte getLinkType() {
        return linkType;
    }

    public void setLinkType(Byte linkType) {
        this.linkType = linkType;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
