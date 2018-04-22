
import com.liuzg.jswebextra.plugins.WXpayPlugin;
import com.liuzg.jswebextra.plugins.pay.model.WXResultData;
import org.junit.Test;

public class WXPayTest {

    @Test
    public void testWx(){
        WXResultData resultData = null;
        WXpayPlugin dodo = null;
        try {
            dodo = new WXpayPlugin();
            /*resultData = dodo.doUnifiedOrder("测试","5423523532","oci96jlJag9f7hMcm9hp9GSUBiM8",0.01,"http://project.neunb.com/wxpay/notify.form","JSAPI","","","","","","","","","","");*/
            resultData = dodo.doRefund("4200000029201710240048154223","","456123789",0.01,0.01,"","","");
            /*resultData = dodo.doOrderQuery("","13731650211");*/
            /*resultData = dodo.doOrderClose("555888777444111");*/
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.print(resultData);

    }

}
