package com.example.parksproject;

import com.example.parksproject.config.AppProperties;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;


@EnableCaching // 캐시 기능 활성화
@EnableBatchProcessing // 배치 기능 활성화
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
