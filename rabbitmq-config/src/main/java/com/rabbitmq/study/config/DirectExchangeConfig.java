package com.rabbitmq.study.config;

import com.rabbitmq.study.constant.MQConstants;
import com.rabbitmq.study.listener.MessageTestListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DirectExchangeConfig {

	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange(MQConstants.MESSAGE_EXCHANGE);
	}

	@Bean
	public Queue bizQueue(){
		Map<String, Object> arguments = new HashMap<String, Object>();
		/*****配置死信队列************/
		arguments.put("x-dead-letter-exchange", MQConstants.DLX_EXCHANGE);
		arguments.put("x-dead-letter-routing-key", MQConstants.DLX_ROUTING_KEY);
		return new Queue(MQConstants.MESSAGE_QUEUE,true,false,false,arguments);
	}

	@Bean
	public Binding bizBinding() {
		return BindingBuilder.bind(bizQueue()).to(directExchange())
				.with(MQConstants.MESSAGE_KEY);
	}

	/**
	 *
	 * 设置监听器
	 */
	@Bean
	public SimpleMessageListenerContainer bizListenerContainer(ConnectionFactory connectionFactory,  MessageTestListener messageTestListener) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(bizQueue());
		container.setExposeListenerChannel(true);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(messageTestListener);
		/** 设置消费者能处理未应答消息的最大个数 */
		container.setPrefetchCount(10);
		return container;
	}

}
