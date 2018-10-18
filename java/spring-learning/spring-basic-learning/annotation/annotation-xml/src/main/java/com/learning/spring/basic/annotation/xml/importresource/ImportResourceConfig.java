package com.learning.spring.basic.annotation.xml.importresource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:importedResource.xml"})
public class ImportResourceConfig {
}
