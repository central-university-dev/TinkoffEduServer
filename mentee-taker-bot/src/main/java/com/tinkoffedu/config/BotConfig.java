package com.tinkoffedu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "mentee.bot")
public class BotConfig {

    private String name;
    private String token;

}
