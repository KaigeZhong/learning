package com.learning.spring.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 查看server是否生效
 * http://localhost:8888/applicationA/default
 * http://localhost:8888/applicationA/dev
 * 如果propertySources不为空且显示出配置文件内容，则表示server没有问题
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
    }
}
