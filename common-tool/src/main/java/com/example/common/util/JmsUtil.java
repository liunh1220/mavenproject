package com.example.common.util;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.jms.JmsSender;

public class JmsUtil {
	
	@Autowired
	private static ApplicationContextUtil appUtil;
	
	/**
	 * 发送文本消息
	 * @param queueName 发送至目标队列
	 * @param text 消息文本内容
	 * @return
	 * @throws NamingException
	 * @throws JMSException
	 */
	public static boolean sendMessage(String queueName,String text) throws Exception
	{
		JmsSender jmsSender = (JmsSender)appUtil.getBean("jmsSender");
		jmsSender.sendMessage(queueName, text);
		return true;
	}
}
