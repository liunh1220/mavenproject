package com.example.user;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 直接启动Provider
 * 
 * @author Administrator
 *
 */
public class ProviderMain {

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "spring.xml" });
			context.start();
			
			System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}