package com.rabbitmq.study.rout.produce;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.study.constant.MQConstants;
import com.rabbitmq.study.util.ConnectionUtils;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class produce1 {
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtils.getConnection();
            channel = connection.createChannel();
            channel.queueDeclare(MQConstants.MESSAGE_QUEUE, false, false, false, null);
            String msg = "hello world :" + new Date();
            channel.basicPublish("", MQConstants.MESSAGE_QUEUE, null, msg.getBytes());
            System.out.println("produce--> msg: " + msg);
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
