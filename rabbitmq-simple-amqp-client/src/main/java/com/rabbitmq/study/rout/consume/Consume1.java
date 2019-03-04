package com.rabbitmq.study.rout.consume;

import com.rabbitmq.client.*;
import com.rabbitmq.study.constant.MQConstants;
import com.rabbitmq.study.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consume1 {
    public static void main(String[] args)  throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(MQConstants.MESSAGE_QUEUE, false, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel){
          @Override
            public void handleDelivery(String consumerTag, Envelope envelope,  AMQP.BasicProperties properties,byte[] body) throws IOException{
              String msgStr = new String(body,"UTF-8");
              System.out.println("consume：" + msgStr);
          }
        };
        //监听
        channel.basicConsume(MQConstants.MESSAGE_QUEUE,true,consumer);
    }
}
