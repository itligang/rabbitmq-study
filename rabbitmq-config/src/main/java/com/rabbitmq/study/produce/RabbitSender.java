package com.rabbitmq.study.produce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.study.constant.MQConstants;
import com.rabbitmq.study.message.RabbitMetaMessage;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitSender implements RabbitSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param rabbitMetaMessage
     * @return
     * @throws Exception
     */
    @Override
    public String send(RabbitMetaMessage rabbitMetaMessage) throws Exception {
        final String msgId = UUID.randomUUID().toString();
        return sendMsg(rabbitMetaMessage, msgId);
    }

    public String sendMsg(RabbitMetaMessage rabbitMetaMessage,String msgId) throws Exception{
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setMessageId(msgId);
            // 设置消息持久化
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rabbitMetaMessage.getPayload());
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(json.getBytes(),messageProperties);
        try {
            rabbitTemplate.convertAndSend(rabbitMetaMessage.getExchange(), rabbitMetaMessage.getRoutingKey(), message, messagePostProcessor, new CorrelationData(msgId));
            return msgId;
        }catch (AmqpException e){
            throw new RuntimeException("发送RabbitMQ消息失败！", e);
        }
    }

    /**
     *
     * 发送到死信队列
     */
    public String sendMsgToDeadQueue(String msg) throws Exception{

        //产生一个消息对象
        RabbitMetaMessage message = new RabbitMetaMessage();
        //设置交换机
        message.setExchange(MQConstants.DLX_EXCHANGE);
        //设置路由
        message.setRoutingKey(MQConstants.DLX_ROUTING_KEY);
        //设置消息体
        message.setPayload(msg);
        //开始发送消息
        return send(message);
    }

}
