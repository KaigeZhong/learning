package com.mybatis.datasource;

import com.mybatis.datasource.dao.CityDao;
import com.mybatis.datasource.dao.DualDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    DualDao dualDao;
    @Autowired
    CityDao cityDao;

    @Bean
    CommandLineRunner sampleCommandLineRunner() {
        return (args) -> {
            System.out.println(dualDao.test());
            System.out.println(cityDao.findByState("CA"));
            System.out.println(cityDao.findbyStateFuzzy("C"));
        };
    }
}
