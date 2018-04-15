package com.example.common.bean;
/**
 * 银泰订单明细
 * @author li.yb
 *
 */
public class TitaiOrderItem {
	private String adjust_fee; //卖家优惠金额
	private String buyer_rate;   //买家是否已评价     
	private String discount_fee;  //系统优惠金额
	private String num;  //购买数量
	private String oid; //子订单编号
	private String outer_sku_id;   //商品编码
	private String payment;    //子订单买家实付金额
	private String pic_path; //商品图片的绝对路径
	private String price;    //商品价格
	private String refund_status;  //退款状态
	private String seller_rate;  //卖家是否已评价
	private String seller_type;  //卖家类型
	private String sku_id;  //商品的最小库存单位Sku的id
	private String sku_properties_name;  //SKU的值
	private String snapshot_url;
	private String status;  //订单状态
	private String title;  //商品标题（只有默认值）
	private String total_fee;  //应付金额
	public String getAdjust_fee() {
		return adjust_fee;
	}
	public void setAdjust_fee(String adjust_fee) {
		this.adjust_fee = adjust_fee;
	}
	public String getBuyer_rate() {
		return buyer_rate;
	}
	public void setBuyer_rate(String buyer_rate) {
		this.buyer_rate = buyer_rate;
	}
	public String getDiscount_fee() {
		return discount_fee;
	}
	public void setDiscount_fee(String discount_fee) {
		this.discount_fee = discount_fee;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOuter_sku_id() {
		return outer_sku_id;
	}
	public void setOuter_sku_id(String outer_sku_id) {
		this.outer_sku_id = outer_sku_id;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public String getSeller_rate() {
		return seller_rate;
	}
	public void setSeller_rate(String seller_rate) {
		this.seller_rate = seller_rate;
	}
	public String getSeller_type() {
		return seller_type;
	}
	public void setSeller_type(String seller_type) {
		this.seller_type = seller_type;
	}
	public String getSku_id() {
		return sku_id;
	}
	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}
	public String getSku_properties_name() {
		return sku_properties_name;
	}
	public void setSku_properties_name(String sku_properties_name) {
		this.sku_properties_name = sku_properties_name;
	}
	public String getSnapshot_url() {
		return snapshot_url;
	}
	public void setSnapshot_url(String snapshot_url) {
		this.snapshot_url = snapshot_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	
	
}
