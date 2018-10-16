package spring.boot.learning.basic.com.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleCommandLineRunner implements CommandLineRunner {
    public void run(String... args) throws Exception {
        System.out.println("spring-boot-learning-basic run");
    }
}
