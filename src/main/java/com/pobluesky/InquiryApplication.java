package com.pobluesky;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.pobluesky.global",
    "com.pobluesky.inquiry",
    "com.pobluesky.lineitem",
    "com.pobluesky.offersheet",
    "com.pobluesky.review",
    "com.pobluesky.quality"
})
@EnableFeignClients(basePackages = "com.pobluesky.feign")
public class InquiryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InquiryApplication.class, args);
    }

    @Bean
    public JPAQueryFactory init(EntityManager em) {
        return new JPAQueryFactory(em);
    }

}
