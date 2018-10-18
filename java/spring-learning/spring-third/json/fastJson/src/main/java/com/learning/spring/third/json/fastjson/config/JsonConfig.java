package com.learning.spring.third.json.fastjson.config;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        /**
         *  1. 需要定义一个converter转换消息的对象
         */
        FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();

        /**
         *  2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
         */
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 序列化配置对象
        SerializeConfig serializeConfig = new SerializeConfig();
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        fastJsonConfig.setSerializeConfig(serializeConfig);
        // 反序列化配置对象
        ParserConfig parserConfig = new ParserConfig();
        parserConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        fastJsonConfig.setParserConfig(parserConfig);

        /**
         *  3. 在converter中添加配置信息
         */
        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fasHttpMessageConverter);
    }
}
