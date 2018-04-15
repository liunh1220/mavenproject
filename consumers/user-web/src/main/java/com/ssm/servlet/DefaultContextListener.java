package com.ssm.servlet;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.util.IPUtil;

public class DefaultContextListener implements ServletContextListener{
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultContextListener.class);
	
	//系统环境变量
	public static final String SYS_ENV_CONFIG = "systemEnvConfig";
	
	public static final String DUBBO_PROVIDER_HOSTS = "dubbo.provider.hosts";
	
	public static final String DUBBO_CONSUMER_HOSTS = "dubbo.consumer.hosts";
	
	public static final String DUBBO_CONFIGS = "dubbo.configs";
	
	public static final String DUBBO_NULL_CONFIG = "classpath*:/aaa-*.xml";
	
	public static final String DUBBO_PROVIDER_CONFIG = "classpath:/dubbo-provider.xml";
	
	public static final String DUBBO_CONSUMER_CONFIG = "classpath:/dubbo-consumer.xml";
	
	public static final String DUBBO_TOTAL_CONFIG = "classpath*:/dubbo-*.xml";
	
	public static final String JMS_CONFIGS = "jms.configs";
	
	public static final String JMS_TOTAL_CONFIG = "classpath*:/jms-*.xml";
	
	public static final String SYS_ENV_CONFIG_SPLIT_MARK = ",";
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 加载Dubbo服务
		this.loadDubbo(event);
		// 设置环境变量(开发环境、测试环境、生产环境)
		this.setEnvironment(event);
		// 加载JMS消息监听服务
		this.loadJMSListener();
	}
	
	private void setEnvironment(ServletContextEvent event)
	{
		String mode = System.getProperty("spring.profiles.active");
		if(mode == null || "".equals(mode))
		{
			mode = "dev";
		}
		logger.info("系统环境spring.profiles.active="+mode);
		System.setProperty("spring.profiles.active", mode);
	}
	
	/**
	 * 加载Dubbo服务
	 */
	private void loadDubbo(ServletContextEvent event)
	{
		String configFile = event.getServletContext().getInitParameter(SYS_ENV_CONFIG);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFile);
		String providers = null;
		String consumers = null;
		String role = new String();
		try {
			Properties properties = new Properties();
			properties.load(is);
			providers = properties.getProperty(DUBBO_PROVIDER_HOSTS);
			consumers = properties.getProperty(DUBBO_CONSUMER_HOSTS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String configFiles = new String();
		
		if(providers != null)
		{
			String[] providerArr = providers.split(SYS_ENV_CONFIG_SPLIT_MARK);
			for (String string : providerArr) {
				if(IPUtil.isLocalIp(string))
				{
					role += "provider";
					logger.info("加载Dubbo提供者...");
					break;
				}
			}
		}
		
		if(consumers != null)
		{
			String[] consumerArr = consumers.split(SYS_ENV_CONFIG_SPLIT_MARK);
			for (String string : consumerArr) {
				if(IPUtil.isLocalIp(string))
				{
					role += "consumer";
					logger.info("加载Dubbo消费者...");
					break;
				}
			}
		}
		
		if("providerconsumer".equals(role))
		{
			configFiles = DUBBO_TOTAL_CONFIG;
		}
		else if("provider".equals(role))
		{
			configFiles = DUBBO_PROVIDER_CONFIG;
		}
		else if("consumer".equals(role))
		{
			configFiles = DUBBO_CONSUMER_CONFIG;
		}
		else
		{
			configFiles = DUBBO_NULL_CONFIG;
		}
		
		System.setProperty(DUBBO_CONFIGS, configFiles);
	}
	
	/**
	 * 加载消息服务监听
	 */
	private void loadJMSListener()
	{
		String config = "";
		
		String jmsListener = System.getProperty("jms.listener");
		
		if(jmsListener == null)
		{
			config = JMS_TOTAL_CONFIG;
			logger.info("加载消息监听服务...");
		}
		else if("Y".equals(jmsListener))
		{
			config = JMS_TOTAL_CONFIG;
			logger.info("加载消息监听服务...");
		}
		else
		{
			config = DUBBO_NULL_CONFIG;
		}
		System.setProperty(JMS_CONFIGS, config);
	}

}
