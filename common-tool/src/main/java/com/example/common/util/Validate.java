package com.example.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	
	/**
	 * 判断字符串是否为E-mail格式
	 * @param emailStr 字符串格式的E-mail
	 * @return boolean
	 */
	public static boolean isEmail(String emailStr)
	{
		boolean isEmail = false;
		if(emailStr==null || "".equals(emailStr))
		{
			return isEmail;
		}
		String patternFormat = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern pattern = Pattern.compile(patternFormat);
		Matcher matcher = pattern.matcher(emailStr);
		if(matcher.matches())
		{
			isEmail = true;
			return isEmail;
		}
		else 
		{
			return isEmail;
		}
	}
	
	/**
	 * 判断密码格式是否合法.<br>
	 * 密码必须为数字、26个英文字母或者下划线组成的字符串
	 * @param passwordStr 字符串格式密码
	 * @return boolean
	 */
	public static boolean passwordIsLegal(String passwordStr)
	{
		boolean isLegal = false;
		String patternFormat = "^\\w+$";
		Pattern pattern = Pattern.compile(patternFormat);
		Matcher matcher = pattern.matcher(passwordStr);
		if(matcher.matches())
		{
			isLegal = true;
			return isLegal;
		}
		else 
		{
			return isLegal;
		}
	}
	
	/**
	 * 判断字符串是否为日期格式
	 * @param dateStr 字符串格式日期
	 * @return boolean
	 */
	public static boolean isDate(String dateStr)
	{
		boolean isDate = false;
		String patternFormat = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|" +
							   "(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|" +
							   "(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)" +
							   "(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		Pattern pattern = Pattern.compile(patternFormat);
		Matcher matcher = pattern.matcher(dateStr);
		if(matcher.matches())
		{
			isDate = true;
			return isDate;
		}
		else
		{
			return isDate;
		}
	}
    
    /**
     * <b>身份证效验规则</b><br>
     * 
     * @param arrIdCard 身份证号码
     * @return
     */
    public boolean isIdCard(String arrIdCard) {   
        int sigma = 0;   
        Integer[] a = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};   
        String[] w = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};   
        for (int i = 0; i < 17; i++) {   
            int ai = Integer.parseInt(arrIdCard.substring(i, i + 1));   
            int wi = a[i];   
            sigma += ai * wi;   
        }   
        int number = sigma % 11;   
        String check_number = w[number];
        
        if (!arrIdCard.substring(17).equals(check_number)) {   
            return false;   
        } else {   
            return true;   
        }   
    }   
}
