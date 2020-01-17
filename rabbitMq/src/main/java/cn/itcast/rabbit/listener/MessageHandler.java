package cn.itcast.rabbit.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository(value = "messageHandler")
public class MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    public void handlerMessage(byte[] message) {


        // 进行业务的处理，通过消息编码拿到对应的接口，然后去调用处理业务就行。
        // 处理失败进行重试机制，后面弄，先把今天的总结一下rabbitMq



    }

}
