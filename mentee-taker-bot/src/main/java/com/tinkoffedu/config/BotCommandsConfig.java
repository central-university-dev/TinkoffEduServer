package com.tinkoffedu.config;

import com.tinkoffedu.service.command.CommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BotCommandsConfig {

    @Bean("botCommandsMap")
    public Map<String, CommandService> botCommandsMap(List<CommandService> commandServices) {
        return commandServices.stream().collect(Collectors.toMap(CommandService::getCommand, Function.identity()));
    }

}
