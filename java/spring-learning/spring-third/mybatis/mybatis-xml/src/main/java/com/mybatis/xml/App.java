package com.mybatis.xml;

import com.mybatis.xml.dao.CityDao;
import com.mybatis.xml.dao.DualDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 直接在Mapper类上面添加注解@Mapper，这种方式要求每一个mapper类都需要添加此注解，麻烦。
 * 通过使用@MapperScan可以指定要扫描的包的路径,
 *@MapperScan(basePackages = {"com.lz.water.monitor.mapper"})
 * 单个人觉得没必要用MapperScan
 */
@SpringBootApplication
//@MapperScan(basePackages = {"com.lz.water.monitor.mapper"})
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
            System.out.println("#########################mybatis xml test start############################");
            System.out.println(dualDao.test());
            System.out.println(cityDao.findByState("CA"));
            System.out.println(cityDao.findbyStateFuzzy("C"));
            System.out.println("#########################mybatis xml test end############################");
        };
    }
}
