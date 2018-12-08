package com.learning.spring.rabbitmq.annotation.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public DirectExchange myExchange(){
        return new DirectExchange("exchange.spring.annotation.demo");
    }

    @Bean
    public Queue myQueue() {
        return new Queue("queue.spring.annotation.demo");
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("myQueue") Queue queueMessage, @Qualifier("myExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("queue.spring.annotation.demo.routingkey");
    }

}
