package com.tinkoffedu.service.notification;

import com.tinkoffedu.dto.notification.MeetingNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class MeetingNotificationService {

    private static final String MESSAGE = """
        MUCHAS GRACIAS AFICION, ESTO ES PARA VOSOTROS!
                
        У вас сейчас встреча с %s. Не смейте пропускать, а то получите по JOPE!!!
                
        SIUUUUUUUUUU!
        """;

    public SendMessage getMessage(MeetingNotification notification) {
        return SendMessage.builder()
            .chatId(notification.chatId())
            .text(MESSAGE.formatted(notification.companionName()))
            .build();
    }

}
