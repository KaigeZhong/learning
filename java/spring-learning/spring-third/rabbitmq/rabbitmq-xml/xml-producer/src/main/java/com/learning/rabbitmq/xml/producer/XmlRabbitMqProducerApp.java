package com.learning.rabbitmq.xml.producer;


import com.learning.rabbitmq.xml.producer.config.ConfigurationImport;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class XmlRabbitMqProducerApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationImport.class);
        AmqpTemplate template = (AmqpTemplate) ctx.getBean("rabbitTemplate");
        template.convertAndSend("queue.spring.xml.demo.routingkey", "hello rabbitmq: spring xml");
    }

}
