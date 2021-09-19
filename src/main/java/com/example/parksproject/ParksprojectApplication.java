package com.example.parksproject;

import com.example.parksproject.config.AppProperties;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ParksprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParksprojectApplication.class, args);
    }

    @Bean
    JPAQueryFactory queryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }


}
