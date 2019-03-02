package com.rabbitmq.study.controller;

import com.rabbitmq.study.constant.MQConstants;
import com.rabbitmq.study.message.RabbitMetaMessage;
import com.rabbitmq.study.produce.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

	@Autowired
	private RabbitSender rabbitSender;

	@GetMapping("produce.do")
	public void produce(String message) throws Exception{
		RabbitMetaMessage rabbitMeta = new RabbitMetaMessage();
		rabbitMeta.setPayload(message);
		rabbitMeta.setExchange(MQConstants.MESSAGE_EXCHANGE);
		rabbitMeta.setRoutingKey(MQConstants.MESSAGE_KEY);
		rabbitSender.send(rabbitMeta);
	}


	@GetMapping("test.do")
	public void test(String message) throws Exception{
		RabbitMetaMessage rabbitMeta = new RabbitMetaMessage();
		rabbitMeta.setPayload(message);
		rabbitMeta.setExchange(MQConstants.MESSAGE_EXCHANGE_TEST);
		rabbitMeta.setRoutingKey(MQConstants.MESSAGE_KEY_TEST);
		rabbitSender.send(rabbitMeta);
	}
	
}
