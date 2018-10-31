package com.learning.spring.third.kafka.producer.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SampleCtrl {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/produce")
    public String produce(@RequestParam String ms) {
        if (ms == null) {
            ms = UUID.randomUUID().toString();
        }
        kafkaTemplate.send("demo", "demo_key", ms);
        return "success";
    }
}
