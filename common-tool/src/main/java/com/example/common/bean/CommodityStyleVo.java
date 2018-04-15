package com.example.common.bean;

import java.io.Serializable;
import java.util.List;

public class CommodityStyleVo  implements Serializable {
	private static final long serialVersionUID = -9027940958607588852L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 商品名称
	 */
	private String commodityName;
	/**
	 * prodDesc
	 */
	private String prodDesc;

	private Integer deleteFlag;
	/**
	 * 商品编号
	 */
	private String no;
	/**
	 * 分类编码
	 */
	private String catNo;
	/**
	 * 品牌编码
	 */
	private String brandNo;

	/**
	 * 分类结构字符串
	 */
	private String catStructname;
	/**
	 * 价格
	 */
	private Double minPrice;
	/**
	 * 分类名称
	 */
	private String catName;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 市场价
	 */
	private Double publicPrice;
	/**
	 * 最高价格
	 */
	private Double maxPrice;
	/**
	 * 默认显示图片(搜索使用)
	 */
	private String defaultPic;
	/**
	 * 供应商款色编码
	 */
	private String supplierCode;
	/**
	 * 销售价
	 */
	private Double salePrice;
	/**
	 * 成本价
	 */
	private Double costPrice;
	/**
	 * 款号
	 */
	private String styleNo;

	/**
	 * 出品年份
	 */
	private String years;
	/**
	 * 规格编码
	 */
	private String specNo;
	/**
	 * 规格名称(一般为颜色的名称)
	 */
	private String specName;
	/**
	 * 成本价2
	 */
	private Double costPrice2;
	/**
	 * 商家编号
	 */
	private String merchantCode;
	/**
	 * 卖点
	 */
	private String sellingPoint;

	/**
	 * 是否微分销商品,1是，0否
	 */
	private Integer isProjNameCommodity;
	/**
	 * 微分销商品编码
	 */
	private String ProjNameCommodityNo;
	/**
	 * 微分销平台上下架状态 1上架，2下架，3未上架
	 */
	private Integer isOnsale;
	/**
	 * ProjNamePrice
	 */
	private Double ProjNamePrice;

	/**
	 * 供应商id
	 */
	private String supplierId;

	/**
	 * 库存
	 */
	private int stock;

	// 剩余时间
	private Long remainTime;

	/**
	 * sku列表
	 */
	private List<ProductVo> products;

	/**
	 * 商品图片
	 */
	private List<PictureVo> pictures;

	/**
	 * 列表mb图，取第一个
	 */
	private String pictureMb;

	/**
	 * 是否是促销商品（0:非促销, 1:促销中, 2:在促销中，被抢完的情况）
	 */
	private Integer isPromotion;

	/**
	 * 活动类型,ActivityTypeEnum
	 */
	private String activityType;
	
	private String ruleDesc;
	private String activityId;
	private String activityNo;
	/**
	 * 促销活动编号
	 */
	private String promotionNo;

	/**
	 * 商品促销价格
	 */
	private Double promotionPrice;

	/**
	 * 商品每单限制购买的数量
	 */
	private Integer limitBuyNumPerOrder;

	/**
	 * 是否支持使用优惠券
	 */
	private Integer couponFlag;

	/**
	 * 单品页广告位
	 */
	private SkuBannerOutputDto skuBannerOutputDto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getCatStructname() {
		return catStructname;
	}

	public void setCatStructname(String catStructname) {
		this.catStructname = catStructname;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Double getPublicPrice() {
		return publicPrice;
	}

	public void setPublicPrice(Double publicPrice) {
		this.publicPrice = publicPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getDefaultPic() {
		return defaultPic;
	}

	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String getSpecNo() {
		return specNo;
	}

	public void setSpecNo(String specNo) {
		this.specNo = specNo;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public Double getCostPrice2() {
		return costPrice2;
	}

	public void setCostPrice2(Double costPrice2) {
		this.costPrice2 = costPrice2;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getSellingPoint() {
		return sellingPoint;
	}

	public void setSellingPoint(String sellingPoint) {
		this.sellingPoint = sellingPoint;
	}

	public Integer getIsProjNameCommodity() {
		return isProjNameCommodity;
	}

	public void setIsProjNameCommodity(Integer isProjNameCommodity) {
		this.isProjNameCommodity = isProjNameCommodity;
	}

	public String getProjNameCommodityNo() {
		return ProjNameCommodityNo;
	}

	public void setProjNameCommodityNo(String ProjNameCommodityNo) {
		this.ProjNameCommodityNo = ProjNameCommodityNo;
	}

	public Integer getIsOnsale() {
		return isOnsale;
	}

	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	public Double getProjNamePrice() {
		return ProjNamePrice;
	}

	public void setProjNamePrice(Double ProjNamePrice) {
		this.ProjNamePrice = ProjNamePrice;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		if (stock == null) {
			this.stock = 0;
			return;
		}
		this.stock = stock;
	}

	public Long getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(Long remainTime) {
		this.remainTime = remainTime;
	}

	public List<ProductVo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVo> products) {
		this.products = products;
	}

	public List<PictureVo> getPictures() {
		return pictures;
	}

	public void setPictures(List<PictureVo> pictures) {
		this.pictures = pictures;
	}

	public String getPictureMb() {
		return pictureMb;
	}

	public void setPictureMb(String pictureMb) {
		this.pictureMb = pictureMb;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public Integer getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(Integer isPromotion) {
		this.isPromotion = isPromotion;
	}

	public String getPromotionNo() {
		return promotionNo;
	}

	public void setPromotionNo(String promotionNo) {
		this.promotionNo = promotionNo;
	}

	public Double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Integer getLimitBuyNumPerOrder() {
		return limitBuyNumPerOrder;
	}

	public void setLimitBuyNumPerOrder(Integer limitBuyNumPerOrder) {
		this.limitBuyNumPerOrder = limitBuyNumPerOrder;
	}

	public Integer getCouponFlag() {
		return couponFlag;
	}

	public void setCouponFlag(Integer couponFlag) {
		this.couponFlag = couponFlag;
	}

	public SkuBannerOutputDto getSkuBannerOutputDto() {
		return skuBannerOutputDto;
	}

	public void setSkuBannerOutputDto(SkuBannerOutputDto skuBannerOutputDto) {
		this.skuBannerOutputDto = skuBannerOutputDto;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	
}
