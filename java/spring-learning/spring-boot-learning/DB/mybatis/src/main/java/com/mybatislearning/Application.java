package com.mybatislearning;


import com.mybatislearning.city.service.CityService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationConfig.class, args);
        CityService cityService = context.getBean(CityService.class);
        cityService.selectByPrimaryKey(1);
    }
}
