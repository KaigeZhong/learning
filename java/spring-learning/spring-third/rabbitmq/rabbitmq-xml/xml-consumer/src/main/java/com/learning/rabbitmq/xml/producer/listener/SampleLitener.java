package com.learning.rabbitmq.xml.producer.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SampleLitener implements MessageListener {
    public void onMessage(Message message) {
        try {
            String body=new String(message.getBody(),"UTF-8");
            System.out.println(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
