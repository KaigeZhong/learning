package com.bean.annotation.configuration.Import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConfigurationByImported.class)
public class ConfigurationImport {
}
