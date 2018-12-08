package com.learning.spring.third.mybatis.function.transaction;

import com.learning.spring.third.mybatis.function.transaction.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Autowired
    CityService cityService;

    @Bean
    CommandLineRunner sampleCommandLineRunner() {
        return (args) -> {
            System.out.println("######################### mybatis transaction test start ############################");
            new Thread(() -> {
                cityService.updateById();
            }).start();

            Thread.sleep(1000L);
//            cityService.insert();
//            cityService.updateById2();
            cityService.updateByName();
            System.out.println("######################### mybatis transaction test end ############################");
        };
    }
}
