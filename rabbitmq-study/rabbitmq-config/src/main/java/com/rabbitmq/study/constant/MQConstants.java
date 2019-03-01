package com.rabbitmq.study.constant;

/**
 *
 * @Autho zl
 * mq相关常量
 */
public class MQConstants {

	public static final String MESSAGE_EXCHANGE = "message.exchange";
	public static final String MESSAGE_QUEUE = "message.queue";
	public static final String MESSAGE_KEY = "message.key";

	public static final String MESSAGE_EXCHANGE_TEST = "message.exchange-test";
	public static final String MESSAGE_QUEUE_TEST  = "message.queue-test";
	public static final String MESSAGE_KEY_TEST  = "message.key-test";



	/**死信队列配置*/
	public static final String DLX_EXCHANGE = "dlx.exchange";


	public static final String DLX_QUEUE = "dlx.queue";

	public static final String DLX_ROUTING_KEY = "dlx.routing.key";






	/**发送端重试乘数(ms)*/
	public static final int MUTIPLIER_TIME = 500;

	/** 发送端最大重试时时间（s）*/
	public static final int MAX_RETRY_TIME = 10;

	/** 消费端最大重试次数 */
	public static final int MAX_CONSUMER_COUNT = 2;

	/** 递增时的基本常量 */
	public static final int BASE_NUM = 2;


	

}
