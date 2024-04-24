package com.tinkoffedu.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartCommandService implements CommandService {

    private static final String COMMAND = "/start";
    private static final String COMMAND_DEFINITION = "Начать работу с ботиком!";
    private static final String MESSAGE = """
        Chipi-chipi, chapa-chapa!
        
        Введите ваш персональный токен c помощью команды
        `/token <твой токен>`
        чтобы мы знали кто вы такой :)
        
        Dubi-Dubi, daba-daba!
        """;

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public String getDefinition() {
        return COMMAND_DEFINITION;
    }

    @Override
    public SendMessage getMessageOnUpdate(Update update) {
        return SendMessage.builder()
            .chatId(update.getMessage().getChatId())
            .text(MESSAGE)
            .parseMode("Markdown")
            .build();
    }
}
