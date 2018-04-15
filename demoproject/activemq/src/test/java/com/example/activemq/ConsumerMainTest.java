package com.example.activemq;

import javax.jms.Destination;
import javax.jms.TextMessage;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerMainTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml"});
		ConsumerService consumer = (ConsumerService) app.getBean("consumerService");
		Destination destination = (Destination) app.getBean("demoQueueDestination");
		
		TextMessage tm = consumer.receive(destination);
		System.out.println("ConsumerMainTest: "+ tm);
	}
	

}
