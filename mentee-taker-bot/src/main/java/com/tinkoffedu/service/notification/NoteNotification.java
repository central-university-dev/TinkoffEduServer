package com.tinkoffedu.service.notification;

import com.tinkoffedu.dto.notification.MeetingNoteNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor
public abstract class NoteNotification {

    @Value("${internal.mentee-taker-api.url}")
    private String menteeTakerApiUrl;

    protected abstract String getMessageText();

    protected abstract String getRequestPath();

    public SendMessage getMessage(MeetingNoteNotification notification) {
        return SendMessage.builder()
            .chatId(notification.chatId())
            .text(getMessageText().formatted(menteeTakerApiUrl + getRequestPath() + notification.noteId()))
            .parseMode("Markdown")
            .build();
    }

}
