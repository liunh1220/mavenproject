package com.example.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry; 

public class ValidateStringUtil {

	private static ValidateStringUtil vs = null;
	
	public static ValidateStringUtil getInstance(){
		if (vs == null) {
			vs = new ValidateStringUtil();
	    }
	    return vs;
	}
	
	//当当网的验证码生成方法
	@SuppressWarnings("rawtypes")
	public  String workByKeySet(Map<String, String> map,String appKey) throws UnsupportedEncodingException{
		    String mdString = null;
		    String[] strTemp = null;
	        if(map != null){
	    	strTemp = new String[map.size()];   
	    	Set<String> key = map.keySet();  
	    	String str = "";
	    	//将map的键名用有规则的字符拼接一起
	        for (Iterator it = key.iterator(); it.hasNext();) {
	            String s = (String) it.next();
	            str += s +";";
	        }
	        //将map键名转换成数组对象
	        strTemp = str.split(";");
	        //将map转换的数组对象按首字母正向排序
	        Arrays.sort(strTemp,String.CASE_INSENSITIVE_ORDER);
	        
	        //map值连接对象(参数值)
	        StringBuffer sbValues = new StringBuffer();
	        //循环取参数名和参数值
	        for (int i = 0; i < strTemp.length; i++) {
	        	sbValues.append(map.get(strTemp[i]).trim());
			}
	        String keyValus = sbValues.toString();
	      //  logger.info("值拼接在一起字符串为："+keyValus);
	      //  logger.info("商家的key为："+appKey);
	        //将字符串进行GBK转码
	        appKey.trim();
	        String keyValusKey = keyValus+appKey;
	        //进行MD5加密
	        mdString = EncryptUtil.makeMd5Sum(keyValusKey.getBytes());
       }
       return mdString;
    }
	
	

	
	private static String bytes2Hex(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (byteArray[i] >= 0 && byteArray[i] < 16) {
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}
	
	
	/**
	 * 新的md5签名，首尾放secret。
	 * 
	 * @param params
	 *            传给服务器的参数
	 * 
	 * @param secret
	 *            分配给您的APP_SECRET
	 */
	public static String md5Signature(TreeMap<String, String> params,
			String secret) {
		String result = null;
		StringBuffer orgin = getBeforeSign(params, new StringBuffer(secret));
		if (orgin == null)
			return result;
		orgin.append(secret);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = bytes2Hex(md.digest(orgin.toString().getBytes("utf-8")));
		} catch (Exception e) {
			throw new java.lang.RuntimeException("sign error !");
		}
		return result;
	}

	/**
	 *  一号店的加密算法
	 * 添加参数的封装方法
	 * 
	 * @param params
	 * @param orgin
	 * @return
	 */
	public static StringBuffer getBeforeSign(TreeMap<String, String> params,
			StringBuffer orgin) {
		if (params == null)
			return null;
		Map<String, String> treeMap = new TreeMap<String, String>();
		treeMap.putAll(params);
		Iterator<String> iter = treeMap.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			orgin.append(name).append(params.get(name));
		}
		return orgin;
	}
	
	private static MessageDigest getMd5MessageDigest() throws Exception {

		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		}

	}
	/*调用接口(API)时需要对请求参数进行签名验证，TOP服务器也会对该请求参数进行验证是否合法的。
	算法

	根据参数名称将你的所有请求参数按照字母先后顺序排序:key + value .... key + value

	对除签名和图片外的所有请求参数按key做的升序排列, value无需编码。

	例如：将foo=1,bar=2,baz=3 排序为bar=2,baz=3,foo=1

	参数名和参数值链接后，得到拼装字符串bar2baz3foo1*/
	public static String signTopRequest(Map<String, String> params, String secret) throws Exception {
		// 第一步：把字典按Key的字母顺序排序

		Map<String, String> sortedParams = new TreeMap<String, String>(params);

		Set<Entry<String, String>> paramSet = sortedParams.entrySet();
		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder(secret);
		for (Entry<String, String> param : paramSet) {
			if (StringUtil.areNotEmpty(param.getKey(), param.getValue())) {
				query.append(param.getKey()).append(param.getValue());
			}
		}
		query.append(secret);
		// 第三步：使用MD5加密

		MessageDigest md5 = getMd5MessageDigest();

		byte[] bytes = md5.digest(query.toString().getBytes("UTF-8"));

		// 第四步：把二进制转化为大写的十六进制

		StringBuilder sign = new StringBuilder();

		for (int i = 0; i < bytes.length; i++) {

			String hex = Integer.toHexString(bytes[i] & 0xFF);

			if (hex.length() == 1) {

				sign.append("0");
			}
			sign.append(hex.toUpperCase());

		}

		return sign.toString();

	}
} 


