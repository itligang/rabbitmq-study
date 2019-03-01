package com.rabbitmq.study.product;

import com.rabbitmq.study.message.RabbitMetaMessage;

public interface RabbitSenderService {

    /**
     * 发送消息
     * @param rabbitMetaMessage
     * @return
     * @throws Exception
     */
     String send(RabbitMetaMessage rabbitMetaMessage) throws Exception;

    /**
     *
     * 发送到死信队列
     */
     String sendMsgToDeadQueue(String message) throws Exception;


}
