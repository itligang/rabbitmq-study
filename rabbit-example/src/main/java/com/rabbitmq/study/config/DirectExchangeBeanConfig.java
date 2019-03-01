package com.rabbitmq.study.config;

import com.rabbitmq.study.constant.MQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeBeanConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MQConstants.MESSAGE_EXCHANGE);
    }

    @Bean
    public Queue bizQueue(){
        return new Queue(MQConstants.MESSAGE_QUEUE,true,false,false);
    }

    @Bean
    public Binding bizBinding() {
        return BindingBuilder.bind(bizQueue()).to(directExchange()).with(MQConstants.MESSAGE_KEY);

    }
}
