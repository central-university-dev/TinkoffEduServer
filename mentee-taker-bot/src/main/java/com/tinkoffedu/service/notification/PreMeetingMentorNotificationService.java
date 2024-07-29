package com.tinkoffedu.service.notification;

import com.tinkoffedu.dto.notification.MeetingNoteNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public class PreMeetingMentorNotificationService  extends NoteNotification {

    private static final String MESSAGE = """
        Дорова!
                
        Ваш менти перед встречей рассказал нам [это](%s).
        """;
    private static final String REQUEST_PATH = "/api/premeeting-note/";

    @Override
    protected String getMessageText() {
        return MESSAGE;
    }

    @Override
    protected String getRequestPath() {
        return REQUEST_PATH;
    }

}
