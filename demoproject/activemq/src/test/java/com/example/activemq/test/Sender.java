package com.example.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory ConnectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD,
				"tcp://localhost:61616");
		Connection connection = ConnectionFactory.createConnection();
		connection.start();
		//使用事务的方式进行消息发送
		//Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		//使用client端签收的方式     +session.commit();
		Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
		Destination destination = session.createQueue("queue1");
		MessageProducer producer = session.createProducer(destination);
		
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		for(int i=0;i<5;i++) {
			TextMessage msg = session.createTextMessage();
			msg.setText("发送的消息内容: "+i);
		    producer.send(destination, msg);
			
			System.out.println("生产者："+msg.getText());
		}
		session.commit();
		if(connection != null) {
			connection.close();
		}
	}

}
