package com.example.common.bean;

import java.util.List;

/**
 * 银泰完整订单信息
 * @author li.yb
 *
 */
public class FullTrades {
	private String seller_nick;   //卖家昵称卖家昵称
	private String buyer_nick;  //买家昵称
	private String created;   //交易创建时间
	private String tid; //交易编号 (父订单的交易编号)
	private String buyer_message;  //买家留言
	private String receiver_name;     //收货人的姓名
	private String receiver_state;  //收货人的所在省份
	private String receiver_city;  //收货人的所在城市
	private String receiver_district;  //收货人的所在地区
	private String receiver_address; //收货人详细地址
	private String receiver_zip;  //收货人的邮编
	private String receiver_phone;    //收货人的手机号码
	private String receiver_mobile;//收货人电话号码
	private String payment;  //实付金额
	private String status; //订单状态
	private String pay_status;   //新加一个付款状态 （付款了写Y没付款写N）
	private String shipping_type;  //创建交易时的物流方式     free(卖家承担运费)   
	private String buyer_alipay_no; //买家支付宝号
	private String buyer_emaill; //买家邮件地址
	private String pay_time;    //付款时间。    
	private String end_time;   //交易成功时间
	private String modified;//交易修改时间(用户对订单的操 作会更新此字段 刚下订单给'' )。
	private String seller_memo;    //卖家备注
	private String buyer_memo;  //买家备注（与淘宝网上订单的买家备注对应）       
	private String trade_memo; //支付宝返回00 财付通11 货到付款2
	private String cod_status;   //支付宝返回00 财付通11 货到付款2
	private String post_fee; //邮费
	private String buyer_obtain_point_fee;  //买家获得积分,返点的积分
	private String buyer_rate;  //其它，默认值为”false”
	private String cod_fee; // 其它,默认值为”0.00”
	private String discount_fee;  //订单折扣金额
	private String has_post_fee; //其它,默认值为”true”
	private String iid; //其它,传“”即可
	private String inv_payee; //发票台头  如 个人，什么公司  
	private String inv_content;   //这个是发票内容  如办公用品，劳保用品  自己定义 如劳保定义1 办公用品定义2 那就返回1或2 就行了
	private String num;   //商品购买数量
	private String orderType;
	private String sourceNo;
	private List<TitaiOrderItem> order;
	private String sourceAmount;
	
	public String getSeller_nick() {
		return seller_nick;
	}
	public void setSeller_nick(String seller_nick) {
		this.seller_nick = seller_nick;
	}
	public String getBuyer_nick() {
		return buyer_nick;
	}
	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getBuyer_message() {
		return buyer_message;
	}
	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_state() {
		return receiver_state;
	}
	public void setReceiver_state(String receiver_state) {
		this.receiver_state = receiver_state;
	}
	public String getReceiver_city() {
		return receiver_city;
	}
	public void setReceiver_city(String receiver_city) {
		this.receiver_city = receiver_city;
	}
	public String getReceiver_district() {
		return receiver_district;
	}
	public void setReceiver_district(String receiver_district) {
		this.receiver_district = receiver_district;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getReceiver_zip() {
		return receiver_zip;
	}
	public void setReceiver_zip(String receiver_zip) {
		this.receiver_zip = receiver_zip;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getShipping_type() {
		return shipping_type;
	}
	public void setShipping_type(String shipping_type) {
		this.shipping_type = shipping_type;
	}
	public String getBuyer_alipay_no() {
		return buyer_alipay_no;
	}
	public void setBuyer_alipay_no(String buyer_alipay_no) {
		this.buyer_alipay_no = buyer_alipay_no;
	}
	public String getBuyer_emaill() {
		return buyer_emaill;
	}
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public void setBuyer_emaill(String buyer_emaill) {
		this.buyer_emaill = buyer_emaill;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getSeller_memo() {
		return seller_memo;
	}
	public void setSeller_memo(String seller_memo) {
		this.seller_memo = seller_memo;
	}
	public String getBuyer_memo() {
		return buyer_memo;
	}
	public void setBuyer_memo(String buyer_memo) {
		this.buyer_memo = buyer_memo;
	}
	public String getTrade_memo() {
		return trade_memo;
	}
	public void setTrade_memo(String trade_memo) {
		this.trade_memo = trade_memo;
	}
	public String getCod_status() {
		return cod_status;
	}
	public void setCod_status(String cod_status) {
		this.cod_status = cod_status;
	}
	public String getPost_fee() {
		return post_fee;
	}
	public void setPost_fee(String post_fee) {
		this.post_fee = post_fee;
	}
	public String getBuyer_obtain_point_fee() {
		return buyer_obtain_point_fee;
	}
	public void setBuyer_obtain_point_fee(String buyer_obtain_point_fee) {
		this.buyer_obtain_point_fee = buyer_obtain_point_fee;
	}
	public String getBuyer_rate() {
		return buyer_rate;
	}
	public void setBuyer_rate(String buyer_rate) {
		this.buyer_rate = buyer_rate;
	}
	public String getCod_fee() {
		return cod_fee;
	}
	public void setCod_fee(String cod_fee) {
		this.cod_fee = cod_fee;
	}
	public String getDiscount_fee() {
		return discount_fee;
	}
	public void setDiscount_fee(String discount_fee) {
		this.discount_fee = discount_fee;
	}
	public String getHas_post_fee() {
		return has_post_fee;
	}
	public void setHas_post_fee(String has_post_fee) {
		this.has_post_fee = has_post_fee;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getInv_payee() {
		return inv_payee;
	}
	public void setInv_payee(String inv_payee) {
		this.inv_payee = inv_payee;
	}
	public String getInv_content() {
		return inv_content;
	}
	public void setInv_content(String inv_content) {
		this.inv_content = inv_content;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public List<TitaiOrderItem> getOrder() {
		return order;
	}
	public void setOrder(List<TitaiOrderItem> order) {
		this.order = order;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getReceiver_mobile() {
		return receiver_mobile;
	}
	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}
	public String getSourceAmount() {
		return sourceAmount;
	}
	public void setSourceAmount(String sourceAmount) {
		this.sourceAmount = sourceAmount;
	}
	

}
