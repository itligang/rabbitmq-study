package com.rabbitmq.study.subpub.consume;

import com.rabbitmq.client.*;
import com.rabbitmq.study.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consume1 {

    private  static  final  String EXCHANGE_NAME = "test_exchange_fanout";
    private  static  final  String QUEUE_NAME = "exchange_fanout_queue_sms";

    public static void main(String[] args)  throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        //如果定义一个匿名内部类，并且希望它使用一个在外部定义的对象，那么编译器器会要求其参数引用是 final 的。
        //jdk1.8 把它默认设置为final的了
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
          @Override
            public void handleDelivery(String consumerTag, Envelope envelope,  AMQP.BasicProperties properties,byte[] body) throws IOException{
              String msgStr = new String(body,"UTF-8");
              System.out.println("consume：" + msgStr);
              //如果定义一个匿名内部类，并且希望它使用一个在外部定义的对象，那么编译器器会要求其参数引用是 final 的。
              channel.basicAck(envelope.getDeliveryTag(),false);
              try {
                  Thread.sleep(500L);
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
          }
        };
        //监听
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);
    }
}
