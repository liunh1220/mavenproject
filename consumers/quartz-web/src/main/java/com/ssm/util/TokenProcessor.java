package com.ssm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;

/**
 * 令牌，用于参数一个随机唯一的令牌值  
 * @author liu.nh
 *
 */
public class TokenProcessor{  
 
  /*private final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',  
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' , 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',  
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };*/  
  private final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',  
          'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' , 
          'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',  
          'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; 
	
  private TokenProcessor(){}  
  
  public static TokenProcessor getInstance(){  
	  return SingletonHolder.instance;  
  }  
  
  private static class SingletonHolder{
      private final static TokenProcessor instance = new TokenProcessor();
  }
  
  private String getRandomString(Integer num) {  //随机的值
	  num = (num==null || num <= 0)? 3: num;
      StringBuffer buffer = new StringBuffer(String.valueOf(System.currentTimeMillis()));  
      for (int i = 0; i < num; i++) {  
          buffer.append(CHARS[new Random().nextInt(CHARS.length)]);  
      }  
      return buffer.toString();  
  }
  
  private String generateToken(){  
      try {  
          MessageDigest md = MessageDigest.getInstance("md5");        //注意下面的处理方式  
          byte[] md5 = md.digest(this.getRandomString(6).getBytes());  
          return new BASE64Encoder().encode(md5); //base64编码    
      } catch (NoSuchAlgorithmException e) {  
          throw new RuntimeException(e);  
      }  
  }  
  
  public static void main(String[] args) {
	  String token = TokenProcessor.getInstance().generateToken();
	  System.out.println(token);
  }
}  
