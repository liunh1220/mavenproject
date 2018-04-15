package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.util.http.HttpCommonParas;
import com.ssm.util.http.HttpRequester;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class YiBoSendUtil {

	private static final Logger logger = LoggerFactory.getLogger(YiBoSendUtil.class);

	/**
	 *  调用方法
	 * 
	 * @param seller
	 *            用户系统级入参
	 * @param params
	 *            方法应用入参
	 * @param method
	 *            请求的方法短URL /Order/GetOrderById
	 * @return  异常异常null 返回。
	 */
	public static String sendYiboUtil(HttpCommonParas seller, Map<String, String> params,
			String method) {
		// 30000
		int connectTimeout = 30000;//ConfigurationUtil.getIntegerValue("chain.connect.timeout");
		// 30000
		int readTimeout = 30000;//ConfigurationUtil.getIntegerValue("chain.connect.timeout");
		String url = "http://data.epoque.cn/Api/Yg/";//ConfigurationUtil.getStringValue("chain.connect.jumei.url");

		//params.put("app_id", seller.getAppId());
		//params.put("app_key", seller.getAppKey());
		//params.put("sign", generateSignKey(params, seller.getAppSession()));

		String resultPost = null;
		try {
			resultPost = HttpRequester.doPost(url + method, params,connectTimeout, readTimeout);
		} catch (Exception e) {
			logger.error("意礡接口异常",e);
		}
		return resultPost;
	}

	
	/**
	 * 生成sign
	 * @param params
	 * @param apiKey
	 * @return
	 */
	private static String generateSignKey(Map<String, String> params,String apiKey) {
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
				String result = URLDecoder.decode(key.concat(params.get(key)), "UTF-8");
				query.append(result);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("",e);
		}
		// 首尾都需要添加
		if (apiKey != null && apiKey.length() != 0) {
			query.append(apiKey);
		}
		return MD5Encrypt(query.toString().getBytes()).toUpperCase();
	}

	/**
	 * Md5 加密
	 * 
	 * @param data
	 * @return
	 */
	private static String MD5Encrypt(byte[] data) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(data);
		} catch (NoSuchAlgorithmException e) {
			logger.error("",e);
			return null;
		}
		byte[] mb = digest.digest();
		StringBuffer hexSb = new StringBuffer();
		String tmp = null;
		for (int i = 0; i < mb.length; i++) {
			tmp = Integer.toHexString(0xff & mb[i]);
			if (tmp.length() == 1){
				hexSb.append('0');
			}
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
		
		HttpCommonParas testSeller = new HttpCommonParas();
//		testSeller.setAppId("");
//		testSeller.setAppKey("");
//		testSeller.setAppSession("");
		
		
        JSONObject json = new JSONObject();
        json.put("pro_no", "code2220");
        json.put("amount", 1);
        
        Object [] items = {json};
        
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("total", "1");
		parameters.put("items", String.valueOf(JSONArray.fromObject(items)));
		String resultPost = YiBoSendUtil.sendYiboUtil(testSeller, parameters,"/push_data");
		
        JSONObject jsonResult = JSONObject.fromObject(resultPost);
		System.out.println("response："+jsonResult);
		System.out.println("response code："+jsonResult.get("code"));
		System.out.println("response msg："+jsonResult.get("msg"));
	
	}

}
