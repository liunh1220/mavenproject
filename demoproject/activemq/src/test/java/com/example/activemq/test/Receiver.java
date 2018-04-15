package com.example.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory ConnectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD,
				"tcp://localhost:61616");
		Connection connection = ConnectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		Destination destination = session.createQueue("queue1");
		MessageConsumer consumer = session.createConsumer(destination);
		while(true) {
			TextMessage msg = (TextMessage) consumer.receive();
			msg.acknowledge();//Session.CLIENT_ACKNOWLEDGE-->手工签收
			System.out.println("消费者收到的消息："+msg.getText());
		}
	}

}
