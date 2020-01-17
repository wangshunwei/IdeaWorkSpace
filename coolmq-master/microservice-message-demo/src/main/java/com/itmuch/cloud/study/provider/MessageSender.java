package com.itmuch.cloud.study.provider;

import com.coolmq.amqp.annotation.TransMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

//import com.coolmq.amqp.annotation.TransMessage;

@Component
public class MessageSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send() {
		String context ="hello" + new Date();
		this.rabbitTemplate.convertAndSend("hello", context);
	}


	@TransMessage(exchange = "exchange.transmsg",bindingKey = "key.transmsg", bizName = "transtest",
	   dbCoordinator = "DBRedisCoordinator")
	public String transSend(){
		System.out.println("........trangsmsg send...........");
		return "hello world";
	}
}
