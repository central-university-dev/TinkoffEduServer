package com.tinkoffedu.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class HelpCommandService implements CommandService {

    private static final String COMMAND = "/help";
    private static final String COMMAND_DEFINITION = "Подсказать что-то, нужна подсказка?";
    private static final String MESSAGE = "В этой жизни тебе никто не поможет, кроме тебя самого!";

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public String getDefinition() {
        return COMMAND_DEFINITION;
    }

    @Override
    public Boolean shouldBeInMenu() {
        return true;
    }

    @Override
    public SendMessage getMessageOnUpdate(Update update) {
        return SendMessage.builder()
            .chatId(update.getMessage().getChatId())
            .text(MESSAGE)
            .build();
    }
}
