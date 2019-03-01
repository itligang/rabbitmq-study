package com.rabbitmq.study.consume;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

public interface MessageListenerService extends ChannelAwareMessageListener {

    void onMessage(Message var1, Channel var2) throws Exception;
}
