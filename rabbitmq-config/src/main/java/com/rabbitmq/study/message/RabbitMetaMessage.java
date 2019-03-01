package com.rabbitmq.study.message;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * message body
 */
@Data
public class RabbitMetaMessage implements Serializable {

    private String exchange;

    private String routingKey;

    private boolean autoTrigger;

    private boolean returnCallback;

    private Object payload;

}
