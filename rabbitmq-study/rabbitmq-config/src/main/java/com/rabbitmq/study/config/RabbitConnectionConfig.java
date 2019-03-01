package com.rabbitmq.study.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * rabbitConnection配置
 *
 * @Author zl
 */

@Configuration
public class RabbitConnectionConfig {

    @Autowired
    MqProperties rabbitProperties;

    /**
     * 创建连接rabbitmq的工厂
     */
    @Bean
    public ConnectionFactory rabbitConnectionFactory() throws Exception {
        RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
        if (rabbitProperties.username != null) {
            factory.setUsername(rabbitProperties.username);
        }
        if (rabbitProperties.password != null) {
            factory.setPassword(rabbitProperties.password);
        }
        if (rabbitProperties.connection_timeout != null) {
            factory.setConnectionTimeout(Integer.valueOf(rabbitProperties.connection_timeout));
        }

        factory.afterPropertiesSet();
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(factory.getObject());
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        if (rabbitProperties.address != null) {
            connectionFactory.setAddresses(shuffle(rabbitProperties.address));
        }
        if (rabbitProperties.size != null) {
            connectionFactory.setChannelCacheSize(rabbitProperties.size);
        }
        if (rabbitProperties.checkout_timeout != null) {
            connectionFactory.setChannelCheckoutTimeout(Integer.valueOf(rabbitProperties.checkout_timeout));
        }
        return connectionFactory;
    }

    String shuffle(String addresses) {
        String[] addrArr = StringUtils.commaDelimitedListToStringArray(addresses);
        List<String> addrList = Arrays.asList(addrArr);
        Collections.shuffle(addrList);
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iter = addrList.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(iter.next());
            if (iter.hasNext()) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }


}
