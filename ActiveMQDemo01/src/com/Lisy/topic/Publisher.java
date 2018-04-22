package com.Lisy.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

import javax.jms.*;

/**
 * Created by NEU on 2017/3/4.
 */
public class Publisher {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址
    ConnectionFactory connectionFactory;//连接工厂
    Connection connection = null;//连接
    Session session;//会话 接受或者发送消息的线程
    MessageProducer messageProducer;//消息的目的地
    Destination[] destinations;//消息生产者

    public Publisher() throws JMSException {

        connectionFactory = new ActiveMQConnectionFactory(Publisher.USERNAME, Publisher.PASSWORD, Publisher.BROKEURL);//实例化连接工厂
        connection = connectionFactory.createConnection();
        try {
            connection.start();
        } catch (JMSException jmse) {
            connection.close();
            throw jmse;
        }
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        messageProducer = session.createProducer(null);
    }
    protected void setTopics(String[] stocks) throws JMSException {
        destinations = new Destination[stocks.length];
        for(int i = 0; i < stocks.length; i++) {
            destinations[i] = session.createTopic("STOCKS." + stocks[i]);
        }
    }
    protected void sendMessage(String[] stocks) throws JMSException {
        for(int i = 0; i < stocks.length; i++) {
            Message message = createStockMessage(stocks[i], session);
            System.out.println("Sending: " + ((ActiveMQMapMessage)message).getContentMap() + " on destination: " + destinations[i]);
            messageProducer.send(destinations[i], message);
        }
    }
    protected Message createStockMessage(String stock, Session session) throws JMSException {
        MapMessage message = session.createMapMessage();
        message.setString("stock", stock);
        message.setDouble("price", 1.00);
        message.setDouble("offer", 0.01);
        message.setBoolean("up", true);

        return message;
    }
    public static void main(String[] args) throws JMSException {
        Publisher publisher = new Publisher();
        publisher.setTopics(args);
        for(int i = 0; i < 10; i++) {
            publisher.sendMessage(args);
            System.out.println("Publisher '" + i + " price messages");
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        publisher.close();
    }
    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }
}