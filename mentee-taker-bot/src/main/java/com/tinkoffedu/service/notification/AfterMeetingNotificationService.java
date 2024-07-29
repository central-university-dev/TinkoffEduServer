package com.tinkoffedu.service.notification;

import com.tinkoffedu.dto.notification.MeetingNoteNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class AfterMeetingNotificationService extends NoteNotification {

    private static final String MESSAGE = """
        Дорова!
                
        У вас есть сутки, чтобы заполнить [форму после встречей](%s). Не смейте пропускать, а то получите по JOPE!!!
        """;
    private static final String REQUEST_PATH = "/api/meeting-note/";

    @Override
    protected String getMessageText() {
        return MESSAGE;
    }

    @Override
    protected String getRequestPath() {
        return REQUEST_PATH;
    }
}
