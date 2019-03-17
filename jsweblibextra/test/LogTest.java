import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by NEUNB_Lisy on 2018/1/2.
 */
public class LogTest {
    private static Log log = LogFactory.getLog(LogTest.class);
    public void log() {
        log.debug("Debug info 测试");
        log.info("Info info 测试");
        log.warn("Warn info 测试");
        log.error("Error info 测试");
        log.fatal("Fatal info 测试");
    }

    public static void main(String[] args) {
        LogTest test = new LogTest();
        test.log();
    }
}
