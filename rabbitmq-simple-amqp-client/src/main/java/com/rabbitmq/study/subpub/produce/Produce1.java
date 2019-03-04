package com.rabbitmq.study.subpub.produce;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.study.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Produce1 {
    private  static  final  String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//分发
        String msg = "test";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        System.out.println("发送："+msg);
        channel.close();
        connection.close();
    }
}
