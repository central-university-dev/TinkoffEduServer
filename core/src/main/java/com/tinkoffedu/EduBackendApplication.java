package com.tinkoffedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.tinkoffedu.*"})
@EnableJpaRepositories(basePackages = {"com.tinkoffedu.*"})
@EntityScan(basePackages = {"com.tinkoffedu.*"})
public class EduBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduBackendApplication.class, args);
    }

}
