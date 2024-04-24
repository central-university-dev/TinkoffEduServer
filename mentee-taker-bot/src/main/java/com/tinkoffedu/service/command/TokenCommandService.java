package com.tinkoffedu.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TokenCommandService implements CommandService {

    private static final String COMMAND = "/token";
    private static final String COMMAND_DEFINITION = "Добавить свой токен для привязки аккаунта";
    private static final String MESSAGE = """
        ОООООО! Чиназес! Сюда-сюда!
        
        Теперь я буду писать тебе, когда мне от тебя будет что-то нужно! НЕ БЛОКИРУЙ, А ТО ТЕБЕ JOPA!
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
            .build();
    }
}
