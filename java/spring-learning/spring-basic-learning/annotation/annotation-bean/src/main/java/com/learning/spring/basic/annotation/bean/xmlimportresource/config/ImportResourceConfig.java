package com.learning.spring.basic.annotation.bean.xmlimportresource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:importedResource.xml"})
public class ImportResourceConfig {
}
