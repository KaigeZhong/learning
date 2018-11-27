package spring.learning.cloud.config.apollo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.learning.cloud.config.apollo.client.config.ApolloConfig;

@SpringBootApplication
public class ApolloClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ApolloConfig.class, args);
    }
}
