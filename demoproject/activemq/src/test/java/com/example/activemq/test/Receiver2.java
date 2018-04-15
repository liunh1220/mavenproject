package com.example.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver2 {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory ConnectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD,
				"tcp://localhost:61616");
		Connection connection = ConnectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建主题   
        Topic topic = session.createTopic("slimsmart.topic.test");  
        // 创建订阅  
        MessageConsumer consumer = session.createConsumer(topic); 
		consumer.setMessageListener(new MessageListener() {  
            // 订阅接收方法  
            public void onMessage(Message message) {  
                TextMessage tm = (TextMessage) message;  
                try {  
                    System.out.println("Received message: " + tm.getText()+":"+tm.getStringProperty("property"));  
                    session.commit();  
                } catch (JMSException e) {  
                    e.printStackTrace();  
                }  
            }  
        });  
	}

}
