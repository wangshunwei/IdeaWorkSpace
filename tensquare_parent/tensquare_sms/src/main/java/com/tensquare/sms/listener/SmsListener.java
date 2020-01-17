package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消费者，消费消息，发送手机短信
 */
@Component
@RabbitListener(queues = "smsqueue")
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    /**
     * 消费消息
     * @param map
     */
    @RabbitHandler
    public void showMsg(Map<String,String> map){
        // 获取到map中的值，给用户发送短信
        // 获取手机号
        String mobile = map.get("mobile");
        // 手机验证码
        String code = map.get("code");
        // 输出
        System.out.println("短信微服务："+mobile+"  --  "+code);
        try {
            // 使用阿里云通信，发送手机短信
            smsUtils.sendSms(mobile,code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

