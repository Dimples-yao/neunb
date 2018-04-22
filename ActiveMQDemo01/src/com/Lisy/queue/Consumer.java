package com.Lisy.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class Consumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址

	ConnectionFactory connectionFactory;//链接工厂
	Connection connection = null;//会话 接受或者发送消息的线程
	Session session;//消息的目的地
	public Consumer() throws JMSException {  
		connectionFactory = new ActiveMQConnectionFactory(Consumer.USERNAME,Consumer.PASSWORD,Consumer.BROKEURL);  
        connection = connectionFactory.createConnection();  
        connection.start();  
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    } 
	public static void main(String[] args) throws JMSException {  
        Consumer consumer = new Consumer();  
        for (String job : Publisher.jobs) {  
            Destination destination = consumer.getSession().createQueue("JOBS." + job);  
            MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);  
            messageConsumer.setMessageListener(new Listener(job));  
        }  
    }  
      
    public Session getSession() {  
        return session;  
    } 
}
