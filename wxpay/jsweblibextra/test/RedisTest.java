import redis.clients.jedis.Jedis;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by NEUNB_Lisy on 2017/12/29.
 */
public class RedisTest {
    public static void main(String[] args){
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("name","lisy");
        jedis.expire("name",1);
        System.out.println("2秒前：" + jedis.get("name"));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("2秒后：" + jedis.get("name"));
            }
        }, 2000);// 设定指定的时间time,此处为2000毫秒
    }
}
