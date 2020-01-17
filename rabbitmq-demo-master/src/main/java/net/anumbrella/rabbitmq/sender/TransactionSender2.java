package net.anumbrella.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TransactionSender2 {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Transactional(rollbackFor = Exception.class)
	public void send(String msg) {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sendMsg = msg + time.format(new Date()) + " This is a transaction message！ ";
		/**
		 * 这里可以执行数据库操作
		 * 
		 **/
		System.out.println("TransactionSender2 : " + sendMsg);

		this.rabbitTemplate.convertAndSend("transition2", sendMsg);

		// 做了业务逻辑，发生啦异常，数据库操作回滚，并且因为配置啦信道事务为true,消息也回滚啦，保持一致性。

	}

}
