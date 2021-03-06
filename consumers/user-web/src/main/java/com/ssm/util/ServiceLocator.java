package com.ssm.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 从spring容器中获取Bean
 *
 * @author 罗正加
 * @date 2011-6-3
 *
 */
@Component
public class ServiceLocator implements BeanFactoryAware {
	private static ServiceLocator serviceLocator = null;
	private static BeanFactory beanFactory = null;

	@SuppressWarnings("static-access")
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static ServiceLocator getInstance() {
		if (serviceLocator == null)
			serviceLocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
		return serviceLocator;

	}
	
	public static Object getBean(String bean)
	{
		return ServiceLocator.getInstance().getBeanFactory().getBean(bean);
	}
}