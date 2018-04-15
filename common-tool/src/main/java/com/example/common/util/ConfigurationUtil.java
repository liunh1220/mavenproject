package com.example.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.manager.IConfigurationManager;

public class ConfigurationUtil {
	
	@Autowired
	private static ApplicationContextUtil appUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationUtil.class);

	public static String getStringValue(String key)
	{
		IConfigurationManager configurationManager = (IConfigurationManager)appUtil.getBean("configurationManagerImpl");
		return configurationManager.getByKey(key);
	}
	
	public static Integer getIntegerValue(String key)
	{
		String value = getStringValue(key);
		if(value != null)
		{
			try
			{
				return Integer.valueOf(value);
			}
			catch(Exception e)
			{
				logger.error("", e);
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public static Double getDoubleValue(String key)
	{
		String value = getStringValue(key);
		if(value != null)
		{
			try
			{
				return Double.valueOf(value);
			}
			catch(Exception e)
			{
				logger.error("", e);
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public static Long getLongValue(String key)
	{
		String value = getStringValue(key);
		if(value != null)
		{
			try
			{
				return Long.valueOf(value);
			}
			catch(Exception e)
			{
				logger.error("", e);
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public static Boolean getBooleanValue(String key)
	{
		String value = getStringValue(key);
		if(value != null)
		{
			try
			{
				return Boolean.valueOf(value);
			}
			catch(Exception e)
			{
				logger.error("", e);
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public static Float getFloatValue(String key)
	{
		String value = getStringValue(key);
		if(value != null)
		{
			try
			{
				return Float.valueOf(value);
			}
			catch(Exception e)
			{
				logger.error("", e);
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public static Date getDateValue(String key,String format)
	{
		String value = getStringValue(key);
		if(value != null)
		{
			try
			{
				return DateUtil.StringToDate(value, format);
			}
			catch(Exception e)
			{
				logger.error("", e);
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public static List<Object> getListValue(String key,String split,@SuppressWarnings("rawtypes") Class clazz)
	{
		String value = getStringValue(key);
		List<Object> result = new ArrayList<Object>();
		try
		{
			if(value != null && value.length() > 0)
			{
				if(clazz == Integer.class)
				{
					String[] values = value.split(split);
					if(values != null && value.length() > 0)
					{
						for (String string : values) {
							result.add(Integer.valueOf(string));
						}
					}
				}
				else if(clazz == Double.class)
				{
					String[] values = value.split(split);
					if(values != null && value.length() > 0)
					{
						for (String string : values) {
							result.add(Double.valueOf(string));
						}
					}
				}
				else if(clazz == Long.class)
				{
					String[] values = value.split(split);
					if(values != null && value.length() > 0)
					{
						for (String string : values) {
							result.add(Long.valueOf(string));
						}
					}
				}
				else if(clazz == Float.class)
				{
					String[] values = value.split(split);
					if(values != null && value.length() > 0)
					{
						for (String string : values) {
							result.add(Float.valueOf(string));
						}
					}
				}
				else if(clazz == Boolean.class)
				{
					String[] values = value.split(split);
					if(values != null && value.length() > 0)
					{
						for (String string : values) {
							result.add(Boolean.valueOf(string));
						}
					}
				}
				else if(clazz == String.class)
				{
					String[] values = value.split(split);
					if(values != null && value.length() > 0)
					{
						for (String string : values) {
							result.add(string);
						}
					}
				}
			}
			
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		
		return result;
	}
	
	public static void setValue(String key,String value)
	{
		try {
			IConfigurationManager configurationManager = (IConfigurationManager) appUtil.getBean("configurationManagerImpl");
			configurationManager.setValue(key, value);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	/**
	 * 赠品，奖品的判断
	 * 
	 * @param title 商品名称
	 * @return
	 */
	public static boolean zhengpingTitle(String title){
		boolean temp = false;
		String tempConfig = ConfigurationUtil.getStringValue("TAOBAO_FUZHUANG_ZENGPING");
		
		String [] tempArrary = tempConfig.split(",");
		
		for (int i = 0; i < tempArrary.length; i++) {
			if(title.indexOf(tempArrary[i]) > -1){
				return true;
			}
		}
		
		return temp;
	}
	
	
	
}
