package com.example.test.multithread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Spring 管理 线程池配置
 * @author liu.nh
 *
 */
public class ThreadPoolTaskExecutorTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ApplicationContext  appContext =new ClassPathXmlApplicationContext( "/spring.xml"); 
		ThreadPoolTaskExecutor taskExecutor=(ThreadPoolTaskExecutor) appContext.getBean("taskExecutor");
		for (int x=0;x<100;x++) {
			taskExecutor.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程"+Thread.currentThread().getName());
				}
			}));
		}
		taskExecutor.shutdown();
	}

}
