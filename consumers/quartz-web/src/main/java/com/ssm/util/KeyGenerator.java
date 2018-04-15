package com.ssm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import sun.misc.BASE64Encoder;

/**
 * 主键生成器
 *
 */
public class KeyGenerator {

	  private final static char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',  
	          'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' , 
	          'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',  
	          'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; 
	  
	  private static String getRandomString(Integer num) {
		  num = (num==null || num <= 0)? 3: num;
	      StringBuffer buffer = new StringBuffer(String.valueOf(System.currentTimeMillis()));  
	      for (int i = 0; i < num; i++) {  
	          buffer.append(CHARS[new Random().nextInt(CHARS.length)]);  
	      }  
	      return buffer.toString();  
	  }
	  
	  public static String getUUID()
	  {
		  String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		  StringBuffer sb = new StringBuffer(uuid);  
		  sb.append(getRandomString(6)); 
		  try {
			  return new BASE64Encoder().encode(MessageDigest.getInstance("md5").digest(sb.toString().getBytes()));
		  } catch (NoSuchAlgorithmException e) {
			  e.printStackTrace();
		  }
		  return uuid;
	  }
	  
	  public static void main(String[] args) throws NoSuchAlgorithmException {
		  System.out.println(getRandomString(6));
		  System.out.println(getUUID());
	  }
}
