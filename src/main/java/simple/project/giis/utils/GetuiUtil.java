package simple.project.giis.utils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simple
 * @date on 2019/1/2 22:12
 */
public class GetuiUtil {
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "sJMIe2pOlE8JtS9JDapVg9";
    private static String appKey = "yBKhMHlHhC8DqeLhj1MMO";
    private static String masterSecret = "3IgHdO0iRB73zG9dXPEcR";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    public static String CID = "";
    //别名推送方式
    public static String Alias = "ys15858120165";

    public static String Tag0 = "sports";

    public static void main(String[] args) throws IOException {

//        String title = null, content = null, type = null;
//        InputStreamReader is = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(is);
//        while (true) {
//            System.out.print("请输入标题： ");
//            title = br.readLine();
//            System.out.print("请输入内容： ");
//            br = new BufferedReader(is);
//            content = br.readLine();
//            System.out.println("title is" + title + ", content is " + content);
//            System.out.print("请选择发送方式（1、普通消息 2、跳转网页 3、跳转下载）： ");
//            br = new BufferedReader(is);
//            type = br.readLine();
//            switch (type) {
//                case "2":
//                    sendMessage(linkTemplateDemo(setStyle(title, content)));
//                    break;
//                case "3":
//                    sendMessage(notyPopLoadTemplateDemo(setStyle(title, content)));
//                    break;
//                case "4":
//                    sendMessage(transmissionTemplateDemo(title, content));
//                    break;
//                default:
//                    sendMessage(notificationTemplateDemo(setStyle(title, content)));
//
//            }
//        }
    }

    public static Style0 setStyle(String title, String content) {
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        //style.setLogo("icon.png");
        // 配置通知栏⽹网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        return style;
    }


    /*
     * 发送给全体
     * */
    public static void sendMessage(ITemplate template) {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());

    }


    /*
     * type: 0 ->向单个clientid用户推送消息
     * type: 1->向单个别名用户推送消息
     * 某用户发生了一笔交易，银行及时下发一条推送消息给该用户
     * */
    public static void sendSingleMessage(int type, String str, ITemplate template) {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        if (type == 0)
            target.setClientId(str);
        else if (type == 1)
            target.setAppId(str);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }

    /*
     *
     * 在通知栏显示一条含图标、标题等的通知，
     * 用户点击后激活您的应用
     * 针对沉默用户，发送推送消息，点击消息栏的通知可直接激活启动应用
     * */
    public static NotificationTemplate notificationTemplateDemo(Style0 style) {

        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(1);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionContent("请输⼊入您要透传的内容");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        template.setStyle(style);
        return template;
    }

    /*
     *
     * 在通知栏显示一条含图标、标题等的通知，
     * 用户点击可打开您指定的网页。
     * 推送广促销活动，用户点击通知栏信息，直接打开到指定的促销活动页面，推送直接到达指定页面
     * */
    public static LinkTemplate linkTemplateDemo(Style0 style) {
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style);
        // 设置打开的⽹网址地址
        template.setUrl("http://www.getui.com");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    /*
     * 消息以弹框的形式展现，
     * 点击弹框内容可启动下载任务
     * 应用有更新，点击推送的更新通知，弹出下载弹窗，点击可启动应用更新下载
     * */
    public static NotyPopLoadTemplate notyPopLoadTemplateDemo(Style0 style) {
        NotyPopLoadTemplate template = new NotyPopLoadTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style);
        // 设置弹框标题与内容
        template.setPopTitle("弹框标题");
        template.setPopContent("弹框内容");
        // 设置弹框显示的图片
        template.setPopImage("");
        template.setPopButton1("下载");
        template.setPopButton2("取消");
        // 设置下载标题
        template.setLoadTitle("下载标题");
        template.setLoadIcon("file://icon.png");
        //设置下载地址
        template.setLoadUrl("http://gdown.baidu.com/data/wisegame/80bab73f82cc29bf/shoujibaidu_16788496.apk"
        );
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    /*
     *
     * 透传消息是指消息传递到客户端只有消息内容，
     * 展现形式由客户端自行定义
     * */
    public static TransmissionTemplate transmissionTemplateDemo(String title, String content) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(2);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionContent(content);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }


}
