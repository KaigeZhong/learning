package com.rabbitmq.learning;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqLProducerApp {
    private static String userName = "guest";
    private static String password = "guest";
    private static String virtualHost = "/";
    private static String hostName = "localhost";
    private static int portNumber = 5672; //for regular connections, 5671 for connections that use TLS
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(hostName);
        factory.setPort(portNumber);
        //等价于factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        /*
        声明exchange
         */
        channel.exchangeDeclare("exchange.demo", BuiltinExchangeType.DIRECT, false, false, null);
        /*
        声明一个队列
        queueDeclare第一个参数表示队列名称、第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
         */
        channel.queueDeclare("queue.demo", false, false, false, null);
        /*
        绑定队列到交换机
         */
        channel.queueBind("queue.demo", "exchange.demo", "routingkey.demo", null);
        String message = "Hello RabbitMQ!";
        //发送消息到队列中
        channel.basicPublish("exchange.demo", "routingkey.demo", null, message.getBytes("UTF-8"));

        channel.close();
        conn.close();
    }
}
