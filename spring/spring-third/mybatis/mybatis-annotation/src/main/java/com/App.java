package com;

import com.dao.CityDao;
import com.dao.DualDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * docker run --rm -p 8080:8080 --name mybatis --network docker_spring_third -d spring-third/mybatis:1.0-SNAPSHOT
 */
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
            System.out.println("#########################mybatis test start############################");
            System.out.println(dualDao.test());
            System.out.println(cityDao.findByState("CA"));
            System.out.println("#########################mybatis test end############################");
        };
    }
}
