package com.example.common.util;

import java.util.Properties;

public class PropertiesUtil {
	
	private static Properties properties = new Properties();
	
	public static String getProperties(String configFile,String key) throws Exception
	{
		properties.load(ClassLoader.getSystemResourceAsStream(configFile));
		String value = properties.getProperty(key);
		value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
		return value;
	}
	
}
