package HelloWorld; /**
 * Created by NEU on 2017/3/6.
 */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv)
            throws java.io.IOException,
            java.lang.InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //设置接收方时与发送方基本相同; 我们打开一个连接和一个通道，并声明我们将要消耗的队列。注意这与发送发布的队列类型应匹配。
        /*注意，因为我们可能在发送方之前启动接收方，所以我们希望在尝试使用消息之前确保队列存在。
        我们将告诉服务器从队列中传递消息。因为它将异步地推送我们消息，所以我们以对象的形式提供一个回调，它将缓冲消息，直到我们准备好使用它们。这就是DefaultConsumer子类做的。*/
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
