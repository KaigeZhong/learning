package com.learning.spring.rabbitmq.annotation.ctrl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/send")
    public String send() {
        String content = "Date:" + new Date();
        rabbitTemplate.convertAndSend("exchange.spring.annotation.demo", "queue.spring.annotation.demo.routingkey", "test:" + content);
        return content;
    }


}
