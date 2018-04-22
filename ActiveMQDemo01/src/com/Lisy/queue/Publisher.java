package com.Lisy.queue;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

	public class Publisher {
		//默认连接用户名
		private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
		//默认连接密码
		private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
		//默认连接地址
		private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

	ConnectionFactory connectionFactory;
	Connection connection=null;
	Session session;
	MessageProducer messageProducer;
	public static String[] jobs={
    		"1","2","3","4","5","6","7","8","9","10"
    }; 
	public Publisher() throws JMSException {  
		
		connectionFactory = new ActiveMQConnectionFactory(Publisher.USERNAME,Publisher.PASSWORD,Publisher.BROKEURL);  
	    connection = connectionFactory.createConnection();  
	    connection.start();  
	    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
	    messageProducer = session.createProducer(null);
	}
	public void sendMessage(String[] jobs) throws JMSException {  
	    for(int i = 0; i < jobs.length; i++)  
	    {  
	        String job = jobs[i];  
	        Destination destination = session.createQueue("JOBS." + job);  
	        Message message = session.createObjectMessage(i);  
	        System.out.println("Sending: id: " + ((ObjectMessage)message).getObject() + " on queue: " + destination);  
	        messageProducer.send(destination, message);  
	    }  
	}
	public static void main(String[] args) throws JMSException {  
	    Publisher publisher = new Publisher(); 
	        publisher.sendMessage(jobs);  
	        System.out.println("Published " + 1 + " job messages");  
	    try {  
	            Thread.sleep(1000);  
	        } catch (InterruptedException x) {  
	        x.printStackTrace();  
	        }  
	        publisher.close();
	}
		public void close() throws JMSException {
			if (connection != null) {
				connection.close();
			}
		}

}
