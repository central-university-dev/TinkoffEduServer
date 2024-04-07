package com.tinkoffedu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bruteforce.protection")
public class BruteforceConfig {

    private Boolean enabled;
    private Long maxAttempts;
    private Long period;

}
