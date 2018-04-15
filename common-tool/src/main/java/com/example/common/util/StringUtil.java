package com.example.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.spec.AlgorithmParameterSpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class StringUtil {

	/**
	 * 过滤XML特殊字符
	 * @return
	 */
	public static String escapeXML(String str)
	{
		if(str != null)
		{
			str = str.replace("<", "(").replace(">", ")").replace("&", " ").replace("'", " ").replace("\\", " ");
		}
		return str;
	}
	
	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value 待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查对象是否为数字型字符串。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		String str = obj.toString();
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}
	
	/**
	 * 过滤不可见字符
	 */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input)))
			return "";
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD)
					|| ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD))
					|| ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}
	
	/**
	 * 输入流转字符串
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream is) throws Exception{
		StringBuffer buffer = new StringBuffer();
		try
	    {
	    	BufferedReader in = new BufferedReader(new InputStreamReader(is));
		    String line = "";
		    while ((line = in.readLine()) != null){
		      buffer.append(line);
		    }
	    }
	    catch (Exception e) {
	    	return null;
		}
	    finally
	    {
	    	is.close();
	    }
	    return buffer.toString();
	}
	
	/*
	 *  把中文字符串转换为十六进制Unicode编码字符串
	 */
	 public static String stringToUnicode(String s) {
	  String str = "";
	  for (int i = 0; i < s.length(); i++) {
	   int ch = (int) s.charAt(i);
	   if (ch > 255)
	    str += "\\u" + Integer.toHexString(ch);
	   else
	     str += "\\" + Integer.toHexString(ch);
	  }
	  return str;
	 }

	/*
	 *  把十六进制Unicode编码字符串转换为中文字符串
	 */
	public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");    
        }
        return str;
    }
	
	//private static final byte[] PASSWORD_CRYPT_KEY = "vjiatest".getBytes();
	//private static final byte[] PASSWORD_CRYPT_IV = "vjia1234".getBytes();

    //加密数据V+
	public static String encrypt(String message,String PASSWORD_CRYPT_KEY,String PASSWORD_CRYPT_IV) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(PASSWORD_CRYPT_KEY.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		AlgorithmParameterSpec secretIv = new IvParameterSpec(PASSWORD_CRYPT_IV.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, secretIv);
  		byte[] pasByte = cipher.doFinal(message.getBytes("utf-8"));
  		return new String(new Base64().encode(pasByte),"utf-8");
	}
	
	// 解密数据
	public static String decrypt(String message,String PASSWORD_CRYPT_KEY,String PASSWORD_CRYPT_IV) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(PASSWORD_CRYPT_KEY.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		AlgorithmParameterSpec secretIv = new IvParameterSpec(PASSWORD_CRYPT_IV.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, secretKey, secretIv);
 		byte[] pasByte = cipher.doFinal(Base64.decodeBase64(message));
 		return new String(pasByte, "UTF-8");
	}
	
	public static String inputStream2String(InputStream in_st,String charset) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
            res.append(line);
        }
        return res.toString();
    }   
	
	
	
	public static void main(String[] args) throws Exception {
		String value = "tqjxFQR0ZiLq5iSx/CDEMg==";
		/*String value ="<result><resultcode>0</resultcode><resultmessage>全部成功</resultmessage> "+
						"<swssupplierid>wxp</swssupplierid><resultdetail> "+
						"<order><orderid>ptN7+pRz41nQjgrpwOitLg==</orderid>
						<orderstatus>+dnQ938sIy8=</orderstatus> "+
						"<orderstatusname>tqjxFQR0ZiLq5iSx/CDEMg==</orderstatusname> "+
						"<orderdistributetime>we2NmNQiCcX95zyNQTngogwpYjH1szLU</orderdistributetime>"+
						"<username>+Eg4H27XQcQ=</username><usertel>tfYw3oEpaOM=</usertel><userphone>"+
						"OmI5PFOGz7nWufbwthvSkQ==</userphone><areaid>NVrYYqhQmS4=</areaid><postalcode>8s6PRquLZhs="+
						"</postalcode><address>GFtHuGq1N5++iR1a5dBu1hUOTXCSAzoudWl/6pYM/CNfvHqISGS5fBgiEKEPHxSS/zO9tr1+SylxQhFyEzKh8lfUJCxzonQOSHzpAbqF6jks0WKQuJ4y4xjnJkI+WqfR</address><needinvoice>W1DYCVPne6o=</needinvoice><receivetime>TArOX4Z+QO2vIRhuCTHfkDKvKAIgKk9ml/osvWRjl+8DeXleuu0QfIbirV+v3IEewWC1eGAq7os=</receivetime><totalprice>+QOkuTQKYxA=</totalprice><transferprice>SZqfGhWvfv0=</transferprice><paidprice>+QOkuTQKYxA=</paidprice><unpaidprice>SZqfGhWvfv0=</unpaidprice><paymenttype>ry3MSOULApZdHrgSvIvILw6ktTaKGzMfk5kx541CYdY=</paymenttype><comment>tfYw3oEpaOM=</comment><orderdetail><barcode>fcFY9+hGbVUZgAksxJZZbw==</barcode><sku>97+cFbbE1zZmW6wXRWKjkQ==</sku><productname>69PqoMtdkHx2YaNZbPrGG/Ij4RprSXBZ0QFUckpAuI+3KMyKEAFHNebSfFokSKDM</productname><size>o08FPPUm/qo=</size><qty>5dyBUWp0VJk=</qty><price>VE7/pUr8OzU=</price><amount>VE7/pUr8OzU=</amount></orderdetail><orderdetail><barcode>fcFY9+hGbVXctmUxjYm+Tw==</barcode><sku>6Un+pfKMv5//rC819vTKNA==</sku><productname>69PqoMtdkHx2YaNZbPrGG/Ij4RprSXBZ0QFUckpAuI+zS/JcZVMpnYtZbwBJp0s1</productname><size>lMxM2ldjNJk=</size><qty>5dyBUWp0VJk=</qty><price>VE7/pUr8OzU=</price><amount>VE7/pUr8OzU=</amount></orderdetail></order></resultdetail></result>";
						*/
		System.out.println("加密数据:" + value);
						
		String a = decrypt(value,"vjiatest","vjia1234");
		System.out.println("加密后的数据为:" + a);
	}
}
