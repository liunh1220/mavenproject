package com.example.http.httprequest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.http.model.Shop;
import com.example.http.util.ConfigurationUtil;
import com.example.http.util.HttpRequester;

public class HttpSendUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpSendUtil.class);

	/**
	 * 调用方法
	 *
	 * @param shop
	 *            用户系统级入参
	 * @param params
	 *            方法应用入参
	 * @param method
	 *            请求的方法短URL /Order/GetOrderById
	 * @return 异常异常null 返回。
	 */
	public static String sendJmUtil(Shop shop, Map<String, String> params, String method) {
		// 30000
		int connectTimeout = ConfigurationUtil.getIntegerValue("chain.connect.timeout");
		// 30000
		int readTimeout = ConfigurationUtil.getIntegerValue("chain.connect.timeout");
		// http://openapi.ext.jumei.com
		String url = ConfigurationUtil.getStringValue("chain.connect.jumei.url");

		params.put("client_id", shop.getAppId());
		params.put("client_key", shop.getAppKey());

		params.put("sign", generateSignKey(params, shop.getAppSession()));

		String resultPost = null;
		try {
			resultPost = HttpRequester.doPost(url + method, params, connectTimeout, readTimeout);
		} catch (Exception e) {
			logger.error("HttpRequester接口异常", e);
			e.printStackTrace();
		}
		return resultPost;
	}

	private static String generateSignKey(Map<String, String> params, String apiKey) {
		StringBuilder query = new StringBuilder();
		// 首尾都需要添加
		if (apiKey != null && apiKey.length() != 0) {
			query.append(apiKey);
		}
		Set<String> sets = params.keySet();
		List<String> lst = new ArrayList<String>(sets);
		Collections.sort(lst);// 按照字典顺序排序
		try {
			for (String key : lst) {
				String result = java.net.URLDecoder.decode(key.concat(params.get(key)), "UTF-8");
				query.append(result);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 首尾都需要添加
		if (apiKey != null && apiKey.length() != 0) {
			query.append(apiKey);
		}
		return MD5Encrpt(query.toString().getBytes()).toUpperCase();
	}

	/**
	 * Md5 加密
	 *
	 * @param data
	 * @return
	 */
	private static String MD5Encrpt(byte[] data) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(data);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] mb = digest.digest();
		StringBuffer hexSb = new StringBuffer();
		for (int i = 0; i < mb.length; i++) {
			String tmp = Integer.toHexString(0xff & mb[i]);
			if (tmp.length() == 1)
				hexSb.append('0');
			hexSb.append(tmp);
		}
		return hexSb.toString();
	}

	/**
	 * 参考测试方法。
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Shop testSeller = new Shop();
		testSeller.setAppId("");
		testSeller.setAppKey("");
		testSeller.setAppSession("");

		// 单个单
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("order_id", "300131257");
		String resultPost = HttpSendUtil.sendJmUtil(testSeller, parameters, "/Order/GetOrderById");

		if (resultPost == null) {

		}

		parameters.clear();
		// 分仓订单列表
		parameters.put("start_date", "2015-09-22 09:55:55");
		parameters.put("end_date", "2015-09-23 09:55:55");
		parameters.put("page", "1");
		parameters.put("page_size", "100");
		parameters.put("shippping_system_id", "1");

		String resultPost2 = HttpSendUtil.sendJmUtil(testSeller, parameters, "/Order/GetOrdersNew");

		if (resultPost2 == null) {

		}

	}

}
