package com.example.common.util;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.Hashing;

/**
 * <b>安全相关类</b>
 *
 * <p>主要包含:</p>
 * <p>1.对称与非对称加密</p>
 * <p>1.1 MD5,SHA,MAC(密钥)</P>
 * <p>2.与对称算法的加密与解密</p>
 * <p>2.1 BASE64(非密钥),DES(密钥)</p>
 */
public class SecurityUtil
{
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
    /**
     * 非对称加密算法MD5
     */
    public static final String ALGORITHM_MD5 = "MD5";  
    
    /**
     * 非对称加密算法SHA
     */
    public static final String ALGORITHM_SHA1 = "SHA-1";  
    
    /**
     * <p>非对称加密算法:</p>
     * <p>MAC算法可选以下多种算法</p>
     * 
     * <p>HmacMD5 HmacSHA1 HmacSHA256 HmacSHA384 HmacSHA512</p>
     * 
     */
    public static final String KEY_MAC = "HmacMD5";
    
    public static final String ALGORITHM_DES = "DES";
    
    private static final String HmacSHA="HmacSHA256";
    
    /**
     * BASE64解密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception
    {
        return Base64.decodeBase64(key);
    }

    /**
     * BASE64加密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception
    {
    	if(key == null){
			return null;
		}
        return new String(new Base64().encode(key),"utf-8");
    }
    
	/**
	 * BASE64加密
	 * 
	 * 将二进制内容编码为base64字符串
	 * @param srcContent 需要编码的数据
	 * @return String 编码结果。如果参数为null，则返回null。
	 */
	public static String encodeBase64(byte[] srcContent)
	{
		if(srcContent == null){
			return null;
		}
		String str = new String(new Base64().encode(srcContent));
		if(str.contains("\r\n")){
			str = str.replace("\r\n", "");
		}
		return str;
	}

	/**
	 * 将二进制内容编码为可以作URL参数的字符串行
	 * 由于默认base64字典中的+和=在URL参数中，都有特殊的含义，需要做转义
	 * 将+转义为-
	 * 将=转义为~
	 * @param srcContent 需要编码为URL参数的数据
	 * @return String 编码结果。如果参数为null，则返回null。
	 */
	public static String encodeURLParam(byte[] srcContent)
	{
		String str = encodeBase64(srcContent);
		if(str.contains("+")){
			str = str.replace('+', '-');
		}
		if(str.contains("=")){
			str = str.replace('=', '~');
		}
		return str;
	}

	/**
	 * 将base64字符串解码为源数据内容
	 * 与encode互为相逆的过程
	 * @param base64Code base64编码字符串
	 * @return byte[] 解码结果。如果参数为null或解码失败，则返回null。
	 */
	public static byte[] decodeBase64(String base64Code)
	{
		if(base64Code == null){
			return null;
		}
		return Base64.decodeBase64(base64Code);
	}

	/**
	 * 将base64字符串解码为源数据内容
	 * 与encodeURLParam互为相逆的过程
	 * @param base64Code base64编码字符串
	 * @return byte[] 解码结果。如果参数为null或解码失败，则返回null。
	 */
	public static byte[] decodeURLParam(String base64Code) throws IOException
	{
		if(base64Code.contains("-")){
			base64Code = base64Code.replace('-', '+');
		}
		if(base64Code.contains("~")){
			base64Code = base64Code.replace('~', '=');
		}
		return decodeBase64(base64Code);
	}
	
    /**
     * MD5加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception
    {
        MessageDigest md5 = MessageDigest.getInstance(ALGORITHM_MD5);
        md5.update(data);
        return md5.digest();

    }

    /**
     * SHA加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception
    {
        MessageDigest sha = MessageDigest.getInstance(ALGORITHM_SHA1);
        sha.update(data);
        return sha.digest();

    }
    
    /**
     * 初始化HMAC密钥
     * 
     * @return
     * @throws Exception
     */
    public static String getMacKey() throws Exception
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception
    {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
        
    }
    
    /**
     * 生成DES密钥
     * 
     * @param seed 生成密钥源数据，一般为要加密的数据
     *             为了生成密钥更安全，建议这个参数不为空
     * @return
     * @throws Exception
     */
    public static String getDESSecretKey(String... seed) throws Exception
    {
        if(seed != null && seed.length > 1){
            throw new Exception("参数数组不能多于1个");
        }
        
        SecureRandom secureRandom = null;

        if (seed != null && seed.length > 0){
            secureRandom = new SecureRandom(decryptBASE64(seed[0]));
        }else{
            secureRandom = new SecureRandom();
        }

        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_DES);
        kg.init(secureRandom);

        SecretKey secretKey = kg.generateKey();

        return encryptBASE64(secretKey.getEncoded());
    }
    
    /**
     * 将生成的DES密钥转换为密钥对象
     * 
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception
    {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

        return secretKey;
    }
    
    /**
     * DES解密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptDES(byte[] data, String key) throws Exception
    {
        Key k = toKey(decryptBASE64(key));

        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.DECRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    /**
     * DES加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptDES(byte[] data, String key) throws Exception
    {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, k);

        return cipher.doFinal(data);
    }
    
    
    public static byte[] encodeHmacSHA256(byte[] data, String key) throws Exception {  
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),HmacSHA);  
        // 实例化Mac  
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());  
        //初始化mac  
        mac.init(secretKey);  
        //执行消息摘要  
        byte[] digest = mac.doFinal(data);  
        return digest;
    } 
    
	/**
	 * 电话号码/身份证/银行卡号的加密处理
	 * @param s_num  前面显示的字符个数
	 * @param e_num  后面显示的字符格式
	 * @param str    被处理的字符串
	 * @return
	 */
	public static String convertEncryptionStr(int s_num, int e_num, String str) {
		
		if(StringUtils.isEmpty(str)){
			return "";
		}
		if(str.length() < s_num + e_num){
			return "";
		}
		String str1 = str.substring(0, s_num);
		String str2 = str.substring(str.length()-(e_num));
		int count = str.length()-(s_num + e_num);
		String rs = str1;
		for(int i=0; i<count; i++){
			rs += '*';
		}
		rs += str2;
		return rs;
	}
	
	   /**
     * 获取AES加密的工具
     *
     */
    public static Cipher AES(byte[] keys, int model) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(model, new SecretKeySpec(keys, "AES"));
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            logger.error("AES加密工具初始化失败", e);
        }
        return null;
    }


    /**
     * 二进制转十六进制字符串
     *
     */
    public static String bytes2Hex(byte bytes[]) {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte b : bytes) {
            // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
            temp = Integer.toHexString(0xFF & b);
            // 每个字节8为，转为16进制标志，2个16进制位
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 十六进制转二进制字符串
     *
     */
    public static byte[] hex2Bytes(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 进行MD5加密
     *
     */
    public static String passwordMd5(String password) {
        try {
            return Hashing.sha256().hashString(password, Charset.forName("utf-8")).toString().toUpperCase();
        } catch (Exception e) {
            logger.error("进行MD5加密发生异常.", e);
        }
        return null;
    }
	
    
    
    public static void main(String[] args) throws Exception
    {
    	byte[] bt = "https://hao.360.cn/?d=100+5".getBytes();
    	String encodeBase64 = encodeURLParam(bt);
    	System.out.println(encodeBase64);
    	
    	byte[] decode = decodeBase64(encodeBase64);
    	String decodeUrl = new String(decode);
    	System.out.println(decodeUrl);
    }
    

}
