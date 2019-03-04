package com.rabbitmq.study.rout.produce;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.study.constant.MQConstants;
import com.rabbitmq.study.util.ConnectionUtils;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class produce2 {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtils.getConnection();
            channel = connection.createChannel();
            channel.queueDeclare(MQConstants.MESSAGE_QUEUE, false, false, false, null);
           //每个消费者 在发送 确认消息之前，消息队列不发送下一个消息到消费者，一次只处理一个消息
            //即限制发送同一个消费者 不得超过一条数据
           // channel.basicQos(1);
            for(int i =0; i<50;i++ ){
                String msg = i+" hello world :" + new Date();
                channel.basicPublish("", MQConstants.MESSAGE_QUEUE, null, msg.getBytes());
                Thread.sleep(10L);
                System.out.println("produce--> msg: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
