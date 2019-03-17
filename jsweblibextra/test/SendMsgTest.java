import com.liuzg.jswebextra.plugins.WXAccesstokenPlugin;
import com.liuzg.jswebextra.plugins.sendmsg.model.MsgResult;
import com.liuzg.jswebextra.plugins.sendmsg.model.Template;
import com.liuzg.jswebextra.plugins.sendmsg.model.TemplateParam;

import java.util.ArrayList;
import java.util.List;

import static com.liuzg.jswebextra.plugins.WXSendMsgPlugin.sendTemplateMsg;

/**
 * Created by NEUNB_Lisy on 2017/12/18.
 */
public class SendMsgTest {
    public static void main(String[] args){
        Template tem=new Template();
        tem.setTemplateId("z4o1Dem7pwPi2zz0i6LvceGBd5X55_PeDw77C-BGwgU");
        tem.setTopColor("#00DD00");
        tem.setToUser("ox8WP0x3MlPNKIHr71RVFAF3IXrw");
        tem.setUrl("www.baidu.com");

        List<TemplateParam> paras=new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("first","您的微信捐款已经成功完成，非常感谢您对慈善项目的关心与支持！","#FF3333"));
        paras.add(new TemplateParam("keyword1","贫困山区孩子的校服梦","#0044BB"));
        paras.add(new TemplateParam("keyword2","100元","#0044BB"));
        paras.add(new TemplateParam("keyword3","2014年7月21日 18:36","#AAAAAA"));
        paras.add(new TemplateParam("remark","让爱心汩汩流淌，温暖城市的每个角落。","#AAAAAA"));

        tem.setTemplateParamList(paras);

        String appId = "wx2e06439b0616043b"; //公众账号的唯一标识

        String appSecret="8be73719b4b79f9af67091f034099e76"; //公众账号的密钥

        MsgResult msgResult = new MsgResult();
        msgResult=sendTemplateMsg(tem);
        System.out.println(msgResult);
    }
}
