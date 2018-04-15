package com.example.http.httprequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.http.model.Shop;
import com.example.http.util.ConfigurationUtil;
import com.example.http.util.HttpsRequester;

public class HttpsSendUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpsSendUtil.class);
	/**
	 * 商品列表查询接口
	 */
	public static final String ICBCB2C_PRODUCT_LIST = "icbcb2c.product.list";
	/**
	 * 商品详情查询
	 */
	public static final String ICBCB2C_PRODUCT_DETAIL = "icbcb2c.product.detail";
	/**
	 * 订单列表查询
	 */
	public static final String ICBCB2C_ORDER_LIST = "icbcb2c.order.list";
	/**
	 * 订单详情查询
	 */
	public static final String ICBCB2C_ORDER_DETAIL = "icbcb2c.order.detail";
	/**
	 * 发货推送
	 */
	public static final String ICBCB2C_ORDER_SENDMESS = "icbcb2c.order.sendmess";
	/**
	 * 库存同步
	 */
	public static final String ICBCB2C_STORAGE_MODIFY = "icbcb2c.storage.modify";

	/**
	 * 请求拼接头
	 */
	public static final String ICBCB2C_HEAD = "<?xml version=" + '"' + 1.0 + '"' + " encoding=" + '"' + "UTF-8" + '"'
			+ "?>";

	/**
	 * 工行接口调用方法
	 * 
	 * @param seller
	 * @param reqDataXml
	 *            根据接口组装xml
	 * @param method
	 *            常量
	 * @return
	 * @throws Exception
	 */
	public static String sendRongEGou(Shop seller, String reqDataXml, String method) throws Exception {
		String resultPost = "";
		try {
			String url = ConfigurationUtil.getStringValue("chain.rongegou.getSku.url");
			if (StringUtils.isBlank(url)) {
				url = "https://218.205.193.4:443/icbcrouter";
			}
			int connectTimeout = ConfigurationUtil.getIntegerValue("chain.connect.timeout");
			int readTimeout = ConfigurationUtil.getIntegerValue("chain.connect.timeout");

			SimpleDateFormat reqidFormart = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			SimpleDateFormat timStpFormart = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");

			Date date = new Date();
			Map<String, String> params = new HashMap<String, String>();
			params.put("app_key", seller.getAppId());
			params.put("app_secret", seller.getAppKey());
			params.put("auth_code", seller.getAppSession());
			params.put("req_data", reqDataXml);
			params.put("timestamp", timStpFormart.format(date));
			params.put("version", "1.0");
			params.put("method", method);
			params.put("format", "xml");
			params.put("req_sid", reqidFormart.format(date));
			params.put("sign", makeSign(params));

			// 请求头信息
			Map<String, String> headMap = new HashMap<String, String>();
			headMap.put("accept-encoding", "gzip,deflate");
			headMap.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

			resultPost = HttpsRequester.doPost(url, params, connectTimeout, readTimeout, headMap);// 提交
		} catch (Exception e) {
			logger.info("调用工行工具类异常", e);
		}
		return resultPost;
	}

	/*
	 * 生成签名
	 * 
	 * @param method HTTP请求方法 "get" / "post"
	 * 
	 * @param url_path CGI名字,
	 * 
	 * @param params URL请求参数
	 * 
	 * @param secret 密钥
	 * 
	 * @return 签名值
	 * 
	 * @throws OpensnsException 不支持指定编码以及不支持指定的加密方法时抛出异常。
	 */
	private static String makeSign(Map<String, String> params) throws Exception {
		String app_secret = params.get("app_secret");
		String app_key = params.get("app_key");
		String auth_code = params.get("auth_code");
		String req_data = params.get("req_data");
		String content = "app_key=" + app_key + "&auth_code=" + auth_code + "&req_data=" + req_data;
		byte[] bytes = encryptHMAC(content.getBytes("UTF-8"), app_secret); // 密文编码字符集使用UTF-8
		Base64 base64 = new Base64();
		return new String(base64.encode(bytes), "UTF-8");
	}

	public static byte[] encryptHMAC(byte[] data, String app_secret) throws Exception {
		SecretKey sk = new SecretKeySpec(app_secret.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance(sk.getAlgorithm());
		mac.init(sk);
		return mac.doFinal(data);
	}

	/*
	 * public static void main(String args[]) throws Exception{
	 * 
	 * String req_data =
	 * "<?Xml version=\"1.0\" encoding=\"UTF-8\"?><body><create_start_time>2013-10-14 12:40:40</create_start_time><create_end_time>2014-12-16 23:09:09</create_end_time><modify_time_from></modify_time_from><modify_time_to></modify_time_to><order_status>2</order_status></body>"
	 * ;
	 * 
	 * HashMap<String, String> params=new HashMap <String, String>();
	 * params.put("req_data", req_data); params.put("app_key", "xvVhd75w");
	 * params.put("app_secret", "APPGVZIkHQ5yZFD9NFaWrIW2xohKDiRz");
	 * params.put("auth_code",
	 * "dsswpAJzHdAXGEIm0EdePvFoUTT5rRT25uspE93RWIhyxrx6VCWFzkBDAtAEdEEf");
	 * 
	 * System.out.println(makeSign(params));//Iqw3/Quvi5yHg3y4eNtTIqb7IuwZpcma/
	 * BMpag2Wu/s= }
	 */

}
