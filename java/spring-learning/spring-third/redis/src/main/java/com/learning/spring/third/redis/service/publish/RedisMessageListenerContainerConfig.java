package com.learning.spring.third.redis.service.publish;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisMessageListenerContainerConfig {

  @Bean
  RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
    MessageListener redisMessageListener) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(redisMessageListener, new PatternTopic("topic[12]"));
    return container;
  }
}
