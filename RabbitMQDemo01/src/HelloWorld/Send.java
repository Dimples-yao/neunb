package HelloWorld;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by NEU on 2017/3/6.
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");//这里连接到本地机器上的代理 ,因此只是localhost。如果我们想连接到不同的机器上，我们只需在这里指定其名称或IP地址。
            Connection connection = null;
            connection = factory.newConnection();
            //创建一个到服务器的链接
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello Lisy!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            //接下来，创建一个通道，这是大部分的API完成驻留的地方。要发送消息前，我们必须声明一个队列给我们发送; 那么我们可以向队列发布消息：
            //声明一个幂等的队列，如果它不存在，则会被创建，消息内容是一个字节数组，所以可以编码其他类型。
            System.out.println(" [x] Sent '" + message + "'");
            channel.close();
            connection.close();
            //关闭通道和链接
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
