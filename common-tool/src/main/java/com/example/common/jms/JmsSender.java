package com.example.common.jms;

import javax.annotation.Resource;
//import javax.jms.JMSException;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
	
	private static final Logger logger = LoggerFactory.getLogger(JmsSender.class);
	
	//@Resource
	//private AmqpTemplate amqpTemplate;
	
	/**
	 * 发送文本消息
	 * @param queueName 发送至目标队列
	 * @param text 消息文本内容
	 * @return
	 * @throws NamingException
	 * @throws JMSException
	 */
	public boolean sendMessage(String queueName,String text) throws Exception
	{
		try {
			//amqpTemplate.convertAndSend(queueName + "_key", text);
		} catch (Exception e) {
			logger.error("send JMS failt.", e);
			throw e;
		}
		return true;
	}

}
