package com.tinkoffedu.service.command;

import com.tinkoffedu.dto.internal.UserTelegramBindRequest;
import com.tinkoffedu.internal.UserInternalApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TokenCommandService implements CommandService {

    private static final String COMMAND = "/token";
    private static final String COMMAND_DEFINITION = "Добавить свой токен для привязки аккаунта";
    private static final String MESSAGE = """
        ОООООО! Чиназес! Сюда-сюда!
                
        %s %s, я теперь знаю всё о тебе и буду писать, когда мне от тебя будет что-то нужно!
        
        НЕ БЛОКИРУЙ, А ТО ТЕБЕ JOPA!
        """;

    private static final String EXCEPTION_MESSAGE = """
        Ты мне какой-то не тот токен передал. Всё фигня, давай по-новой!
        """;

    private final UserInternalApi userInternalApiService;

    public TokenCommandService(@Qualifier("userInternalApiClient") UserInternalApi userInternalApiService) {
        this.userInternalApiService = userInternalApiService;
    }

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
        return false;
    }

    @Override
    public SendMessage getMessageOnUpdate(Update update) {
        var text = update.getMessage().getText();
        var token = text.substring(text.lastIndexOf(COMMAND) + COMMAND.length()).trim();
        var result = userInternalApiService.addUserTelegram(
            new UserTelegramBindRequest(token, update.getMessage().getChatId())
        );
        return SendMessage.builder()
            .chatId(update.getMessage().getChatId())
            .text(result.error() == null ? MESSAGE.formatted(result.firstName(), result.lastName()) : EXCEPTION_MESSAGE)
            .build();
    }

}
