package com.example.ehcache.util;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class DataSourceAdvice implements MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());

	// dao方法执行之前被调用
	public void before(Method method, Object[] args, Object target)
			throws Throwable {

		log.info("切入点: " + target.getClass().getName() + "类中"
				+ method.getName() + "方法");
//		if (method.getName().startsWith("find")
//				|| method.getName().startsWith("load")
//				|| method.getName().startsWith("query")
//				|| method.getName().startsWith("list")
//				|| method.getName().startsWith("get")
//				|| method.getName().startsWith("search")
//				|| method.getName().startsWith("c_")) {
//			log.info("查询切换到: slave");
//			DataSourceSwitcher.setSlave();
//		} else {
//			log.info("操作切换到: master");
//			DataSourceSwitcher.setMaster();
//		}
//		
		if ( method.getName().startsWith("c_")||
				method.getName().startsWith("u_")||
				method.getName().startsWith("subCancelOrder")) {
			log.info("操作切换到: master");
			DataSourceSwitcher.setMaster();
		} else {
			log.info("查询切换到: slave");
			DataSourceSwitcher.setSlave();
		}
	}

	// dao方法执行完之后被调用
	public void afterReturning(Object arg0, Method method, Object[] args,
			Object target) throws Throwable {
	}

	// 抛出Exception之后被调用
	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) throws Throwable {
		log.info("抛出异常数据操作切换到: master");
		DataSourceSwitcher.setMaster();
	}

}
