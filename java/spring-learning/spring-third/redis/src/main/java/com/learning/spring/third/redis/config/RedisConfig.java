//package com.learning.spring.third.redis.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * 如果使用spring boot默认的两种redisTemplate，则不需要此配置类
// */
//@Configuration
//public class RedisConfig{
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    /**
//     * RedisConnectionFactory不需要配置，默认会采用Lettuce，当然也可以采用定制化的配置
//     */
////    @Bean
////    public RedisConnectionFactory redisConnectionFactory() {
////        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration(host, port);
////        //redisConfiguration.setPassword(RedisPassword.of(password));
////        return new LettuceConnectionFactory(redisConfiguration);
////    }
//    /**
//     * RedisConnectionFactory 默认为Lettuce
//     */
//    @Bean
//    public RedisTemplate<String,Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(redisSerializer);
//        redisTemplate.setHashKeySerializer(redisSerializer);
//        //JdkSerializationRedisSerializer序列化方式;
//        JdkSerializationRedisSerializer jdkRedisSerializer=new JdkSerializationRedisSerializer();
//        redisTemplate.setValueSerializer(jdkRedisSerializer);
//        redisTemplate.setHashValueSerializer(jdkRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//}