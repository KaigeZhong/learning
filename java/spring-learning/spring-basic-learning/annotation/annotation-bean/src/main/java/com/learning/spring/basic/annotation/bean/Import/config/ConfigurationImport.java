package com.learning.spring.basic.annotation.bean.Import.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConfigurationByImported.class)
public class ConfigurationImport {
}
