package cn.itcast.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "specialineListener")
// 可以添加多个队列，代表这个类能够监听到的队列名称
@RabbitListener(queues = {"mq.test"},
                containerFactory = "specialineListenerContainerFactory")
public class Recevier {

    @Autowired
    private MessageHandler messageHandler;
    // 通过这个方法进行处理消息内容
    @RabbitHandler
    public void process(byte[] message) {
        messageHandler.handlerMessage(message);
    }

}
