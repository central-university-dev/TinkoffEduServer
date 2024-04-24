package com.tinkoffedu.service.command;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
public class CommandsHandler {

    private final Map<String, CommandService> commands;

    public CommandsHandler(@Qualifier("botCommandsMap") Map<String, CommandService> commands) {
        this.commands = commands;
    }

    public SendMessage apply(Update update) {
        var command = update.getMessage().getText().split(" ")[0];
        var service = commands.get(command);
        return service == null ? UnknownCommandService.getMessageOnUpdate(update) : service.getMessageOnUpdate(update);
    }

}
