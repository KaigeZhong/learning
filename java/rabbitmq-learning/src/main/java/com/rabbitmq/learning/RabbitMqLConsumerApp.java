package com.rabbitmq.learning;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitMqLConsumerApp {
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
        // 等价于 factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        //声明要关注的队列
//        channel.queueDeclare(QUEUE_NAME, false, false, true, null);
//        System.out.println("Customer Waiting Received messages");
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        channel.basicConsume("queue.demo", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        });

//        channel.close();
//        conn.close();
    }
}
