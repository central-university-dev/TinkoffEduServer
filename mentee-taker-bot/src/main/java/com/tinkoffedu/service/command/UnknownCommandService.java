package com.tinkoffedu.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UnknownCommandService {

    private static final String MESSAGE = """
        Так, это что такое, а?
        Ты что-то в натуре попутал, давай всё по-новой!
        """;

    public static SendMessage getMessageOnUpdate(Update update) {
        return SendMessage.builder()
            .chatId(update.getMessage().getChatId())
            .text(MESSAGE)
            .build();
    }
}
