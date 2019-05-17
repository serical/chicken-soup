package com.serical.chickensoup;

import com.serical.chickensoup.config.ChickenSoupConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ChickenSoupConfig.class)
public class ChickenSoupApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChickenSoupApplication.class, args);
    }

}
