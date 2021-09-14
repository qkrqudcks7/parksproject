package com.example.parksproject;

import com.example.parksproject.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ParksprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParksprojectApplication.class, args);
    }
}
