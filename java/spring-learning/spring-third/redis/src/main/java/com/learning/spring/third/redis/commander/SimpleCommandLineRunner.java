package com.learning.spring.third.redis.commander;

import com.learning.spring.third.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RedisService redisService;

    public void run(String... args) throws InterruptedException {
        redisService.set("a", "aa");
        System.out.println(redisService.get("a"));
        System.out.println(redisService.hasKey("a"));
        redisService.del("a");
        System.out.println(redisService.hasKey("a"));
        redisService.setAndExpire("expire", "V", 2);
        System.out.println(redisService.hasKey("expire"));
        redisService.getExpire("expire");
        Thread.sleep(2000L);
        System.out.println(redisService.hasKey("expire"));

        redisService.hset("hash", "a", "aaa");
        redisService.hset("hash", "b", "bbb");
        System.out.println(redisService.hget("hash", "a"));
        System.out.println(redisService.hmget("hash"));

        redisService.publish("topic1", "topic1_message");
        redisService.publish("topic2", "topic1_message");
        redisService.publish("topic3", "topic1_message");
    }
}
