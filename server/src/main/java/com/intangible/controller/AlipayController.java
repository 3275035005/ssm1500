package com.intangible.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import com.intangible.entity.Order;
import com.intangible.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *  支付账号 ohsxej8116@sandbox.com
 *  支付密码 111111
 *  https://open.alipay.com/develop/sandbox/app
 *
 */
@Controller
public class AlipayController {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private final String APP_ID = "9021000131687890";

    // 应用私钥，您的PKCS8格式RSA2私钥，通过支付宝开放平台密钥工具获取
    private final String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCxcMrkjyc/VgJO7VDuT89SlwQSNap+WoWWovPIPqQ9DJNqZ4Mhn9kN2CvTBsA57R81qgpiHjPvYmIZd3BjDdAHiTJkfvd4YZlvKqrOGbZ7+X4MOfJccl+zzjJR11Nr/JZlB43Naw4oO9z+/xMaESYAoS/aHujkIyn+wYB8+XhUD1DI1v9cx47L3OgDa0GoHRjTIdRZNtM0gZQsQSP1Chu3MQ9J9d9o5e1kGW4DyGruupzyRJV/u8wYRTby5fLH9YqR5tLz8CUgQnuDFWwCvX7OO3kQuADv+/9vxbqUrc2av8G/HLObLtndBMFGUAADnwcby9q97UjMhXJK+iPtqjmVAgMBAAECggEBAK8gLX+LMic2kiyn2Jp8NpjC54e5Y741RWe5CHX4LUz7nnsEEs4a37TkR8yM+yewun7ShlD0kjmPllagJVH+DfWCGTRKg6BSY4buS4oCorKVOSyD/In1cDMcLVnM9U69CwpHfFLjeCOJgcJCqe2uQx2X+IyEuUKA2i/PdyrhZTs//wm0uDGzgeYtNp77lJqsFRtNP0CH7zU032i5mGOBy3qeP1ACfnymdPy8UYZtz2q4pzY4DlVdjCFhoFuT674RT++1p/nnEDNEB5DliCHSyEocXD3VuolYwgfSimFqdjO5GdBDuMJM+ZppHfbAZ9saLGUiHwuqPTExdXJZSilxiEECgYEA/rSc7BE4B3+2iPAY23GdLY+7P8GrYz7KYPVS2trBC8Qe2aW0yH9NKMUxLIcYw2xVAnIeZkMS87s1paWGBHBmuwhS1YbDNxmfF/OuL6FVq42i3evym8wWyZ0bMGiWmGjW4xzsL3q15PQKpGrFyyVNIbNsF3KlkVp4ZJkNiNGR4dkCgYEAslenP5bnKgthMH25JWH6RaWHlB4vZzNavirfDSs/dvI/vZmLjtaELEeDaM8dS2qmUHA1vpd13tGqzWq1goHWzbJqocXdwc7M4yooY7M31QmosHGh1B8LvPjvoncs17//kM81KJcW6/fCtalqoOtDClpQV6x6rpshcRGfHBOWRB0CgYAtqWl8dLXLulyZ/hkmth6MfXj/PBQira4QZcqdy5vSJS22rRtU2mWVyow/RFTvK+utjZHfmWS7ZzN958e34gK4www+lXNH3IMGAzyV82FtaO3qxRLwVU08hbrLGeTtm9XyT0wUUvZq/kkuIqok+iHyyby2E2qVPVfaCQpBHfj+OQKBgQCv5hI4ft7rMiQezrHQ9C0DinsJlSgN8rHahLBQ7cn/yHkjyRDDxkLA5ZvvwO/U1ZDKH5JytDJ2nXeIeWojGWrCoOnZOy44nH6qw4bopvg3b/poZPtHG4PZU/IO3NpmxaEMs8JrkXHzNQBfaoSXyNLroB5ECwNJugom2kmDSLJZ5QKBgQDBjXrD3AucBnIZDtaEeL/6Svizor8t3A//Z6PI4hk1jujrnukPH4r58RxqefVnruBnhCzwFeHBiNB+BId1b9izqxYP5XBw1lBbyrne+QXv/Dh/tJXH2VfefAkeirmMnfEnZOUtNxVvCBPk9pK7S97bGDzCst+cfPHREPQTE2HZ4g==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmG4ARx6/o7Hl6bWeLNgtJBKsjbcEEfg7b6fK0T4lzi6tUoaxxMAt5s/DQeaq5Oa1bboh15OSLoQwvG3MzYL7Ch7WHIO2yarGZtuTQBrGDISMXOFxIf1rsvlnQybE5gnAqfJQa5vA5Mv22bmyMFmaQxA3GSqtCqFdlppVSMbxdaib/WWHA8iq2MGXZh31RUc1PK6jBoYmZa+vDT7WXjBcZJLU8WxiZjp+FN0r/BdOsfFI3hYml7tUHgrHQnaQVzC8/1R6OJqMG9DSBAcuqVwSXQwueS2KfVTdm9N0m01pWrULosby6j0L/lYqs7rT3WMknkP4yR11J0hxfYeWzWRkNwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private final String NOTIFY_URL = "http://localhost:8081/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
    private final String RETURN_URL = "http://localhost:8081/alipayReturnNotice";

    // 签名方式
    private final String SIGN_TYPE = "RSA2";

    // 字符编码格式
    private final String CHARSET = "utf-8";

    // 支付宝网关
    private final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    //
    private final String FORMAT = "JSON";


    @Autowired
    private OrderService orderService;

    @RequestMapping("/alipay")
    public void alipay(HttpServletResponse httpResponse, String orderNo) throws IOException {

        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //生成随机Id
//        String outTradeNo = UUID.randomUUID().toString();

        Order order = orderService.getOrderById(orderNo);
        System.out.println(order);
        //付款金额，必填
//        String totalAmount =Integer.toString(19);
        //订单名称，必填
        String subject = "充值视频会员1月";
        //商品描述，可空
        String body = "";
        request.setBizContent("{\"out_trade_no\":\""+ orderNo +"\","
                + "\"total_amount\":\""+ order.getPrice() +"\","
                + "\"subject\":\""+ order.getGoodsName() +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    // 支付成功回调
    @RequestMapping(value = "/alipayReturnNotice")
    public String returnUrl(String out_trade_no){
        System.out.println(out_trade_no);
        //
        Order order = orderService.getOrderById(out_trade_no);
        order.setStatus("1");
        orderService.update(order);

        return "redirect:http://localhost:8080/#/order?id="+out_trade_no;
    }

}

