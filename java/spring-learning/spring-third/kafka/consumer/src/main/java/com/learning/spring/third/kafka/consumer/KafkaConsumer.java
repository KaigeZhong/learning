package com.learning.spring.third.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"demo"})
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("key: " + record.key());
        System.out.println("value: " + record.value());
    }
}
