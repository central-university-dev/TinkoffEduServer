package com.tinkoffedu.service.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandService {

    String getCommand();

    String getDefinition();

    Boolean shouldBeInMenu();

    SendMessage getMessageOnUpdate(Update update);
}
