package com.learning.spring.third.redis.service.publish;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageListener implements MessageListener {
  @Override
  public void onMessage(Message message, byte[] bytes) {

    byte[] body = message.getBody();
    byte[] topic = message.getChannel();
    System.out.println("redis message listener: " + new String(topic));
    System.out.println("redis message listener: " + new String(body));
  }
}
