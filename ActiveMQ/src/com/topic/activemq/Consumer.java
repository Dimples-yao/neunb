package com.topic.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.DecimalFormat;

/**
 * Created by NEU on 2017/3/4.
 */
public class Consumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址

    javax.jms.ConnectionFactory ConnectionFactory;//链接工厂
    Connection connection;
    Session session;
    public Consumer() throws JMSException {
        ConnectionFactory = new ActiveMQConnectionFactory(Consumer.USERNAME,Consumer.PASSWORD,Consumer.BROKEURL);
        connection = ConnectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
    public static void main(String[] args) throws JMSException {
        Consumer consumer = new Consumer();
        for (String stock : args) {
            Destination destination = consumer.getSession().createTopic("STOCKS." + stock);
            MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
            messageConsumer.setMessageListener(new Listener());
        }
    }

    public Session getSession() {
        return session;
    }
    public static class Listener implements MessageListener {

        public void onMessage(Message message) {
            try {
                MapMessage map = (MapMessage)message;
                String stock = map.getString("stock");
                double price = map.getDouble("price");
                double offer = map.getDouble("offer");
                boolean up = map.getBoolean("up");
                DecimalFormat df = new DecimalFormat( "#,###,###,##0.00" );
                System.out.println(stock + "\t" + df.format(price) + "\t" + df.format(offer) + "\t" + (up?"up":"down"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
