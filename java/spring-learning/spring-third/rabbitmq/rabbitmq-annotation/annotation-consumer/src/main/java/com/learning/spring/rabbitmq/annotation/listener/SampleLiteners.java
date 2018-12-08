package com.learning.spring.rabbitmq.annotation.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SampleLiteners {
    @RabbitListener(queues="queue.spring.annotation.demo")
    public void processA(String str1) {
        System.out.println("Received:"+str1);
    }
}
