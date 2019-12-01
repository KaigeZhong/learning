package com.mybatislearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.mybatislearning.**.mapper")
public class ApplicationConfig {

}
