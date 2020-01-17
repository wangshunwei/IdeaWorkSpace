package cn.itcast.rabbit.rabbit;

import cn.itcast.rabbit.spring.MultiRabbitTemplateFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

/**
 *
 * 这边只是将交换机的信息和路由的routingKey发送过去，然后经办那边进行绑定，根据我们发送的，去找相应的队列
 *
 *
 */
@Service("cn.itcast.rabbit.Sender")
public class Sender implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnCallback{

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    /**
     * 发送给消息的方法 里面具体处理业务数据
     * @param exchange
     * @param routingKey
     * @param msgCode
     * @param mpService
     * @param message
     * @param ownAreaCd
     * @return
     */
    public String sendMsg(String exchange,String routingKey,String msgCode,String mpService,String message,String ownAreaCd) {

        RabbitTemplate rabbitTemplate = MultiRabbitTemplateFactory.getBeanByName(mpService + "RabbitTemplate");

        if (rabbitTemplate == null) {
            return "not found mq-service by mqservice:" + mpService;
        }
        if (!rabbitTemplate.isConfirmListener()) {
            rabbitTemplate.setConfirmCallback(this);
        }
        // 处理业务逻辑数据 ，先不要是用到JSON字符串的处理，需要存什么字段发送什么字段。
        JSONObject comJson = new JSONObject();
        // 添加消息编码
        comJson.put("messgeCode", msgCode);
        // 添加路由键
        comJson.put("area", routingKey);
        // 添加ip
        try {
            comJson.put("ip", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            logger.error("Ip parse error",e);
        }
        // 添加时间戳
        comJson.put("timeStamp", Calendar.getInstance().getTimeInMillis());
        CorrelationData unique = new CorrelationData();
        // 将object转换为string
        String msg = JSON.toJSONString(comJson);
        try {
            rabbitTemplate.convertAndSend(exchange,routingKey,msg.getBytes("UTF-8"),unique);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("send fail");
        }

        return "";
    }



    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {

        if (ack) {
            if (logger.isInfoEnabled()) {
                logger.info("发送成功");
            }
        } else {
            logger.error(" send fail");
        }

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
