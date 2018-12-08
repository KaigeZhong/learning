package com.learning.rabbitmq.xml.producer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:importedResource.xml"})
public class ConfigurationImport {
}
