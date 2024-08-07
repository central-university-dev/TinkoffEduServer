package com.tinkoffedu.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tinkoffedu.interceptor.InternalJwtInterceptor;
import com.tinkoffedu.internal.NotificationInternalApi;
import com.tinkoffedu.internal.UserInternalApi;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MenteeTakerBotInternalApiConfig {

    @Value("${internal.mentee-taker-bot-api.url}")
    private String menteeTakerBotApi;
    private final InternalJwtInterceptor jwtInterceptor;

    @Bean("notificationInternalApiClient")
    public NotificationInternalApi notificationInternalApiClient() {
        return Feign.builder()
            .encoder(new JacksonEncoder(List.of(new JavaTimeModule())))
            .decoder(new JacksonDecoder(List.of(new JavaTimeModule())))
            .contract(new SpringMvcContract())
            .logger(new Slf4jLogger(UserInternalApi.class))
            .logLevel(Logger.Level.FULL)
            .requestInterceptor(jwtInterceptor)
            .target(NotificationInternalApi.class, menteeTakerBotApi);
    }

}
