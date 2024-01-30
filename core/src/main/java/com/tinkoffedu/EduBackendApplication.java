package com.tinkoffedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class EduBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduBackendApplication.class, args);
    }

}
