package com.rabbitmq.study.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {

    public  static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("123456");
        return factory.newConnection();
    }

//    public static void main(String[] args) throws IOException, TimeoutException {
//        Connection connection = ConnectionUtils.getConnection();
//        System.out.println(connection.getAddress());
//        connection.close();
//    }
}
