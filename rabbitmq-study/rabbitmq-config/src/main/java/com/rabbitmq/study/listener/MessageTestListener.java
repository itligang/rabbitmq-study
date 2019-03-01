package com.rabbitmq.study.listener;

import com.rabbitmq.study.consume.AbstractMessageListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class MessageTestListener extends AbstractMessageListener {


    @Override
    public void receiveMessage(Message message, MessageConverter messageConverter) {
        System.out.println(message.toString());
    }
}
