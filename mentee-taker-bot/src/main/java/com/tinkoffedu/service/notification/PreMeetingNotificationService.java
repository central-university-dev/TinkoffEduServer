package com.tinkoffedu.service.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class PreMeetingNotificationService {

    private static final String MESSAGE = """
        MUCHAS GRACIAS AFICION, ESTO ES PARA VOSOTROS!
                
        У вас сейчас встреча с %s. Не смейте пропускать, а то получите по JOPE!!!
                
        SIUUUUUUUUUU!
        """;

    public SendMessage getMessage(Update update) {
        return SendMessage.builder()
            .chatId(update.getMessage().getChatId())
            .text(MESSAGE)
            .parseMode("Markdown")
            .build();
    }

}
