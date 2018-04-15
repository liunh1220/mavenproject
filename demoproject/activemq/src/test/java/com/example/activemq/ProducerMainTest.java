package com.example.activemq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerMainTest {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml"});
		ProducerService producer = (ProducerService) app.getBean("producerService");
		
		String msg = "producer.sendMessage";
        producer.sendMessage(msg);
	}

}
