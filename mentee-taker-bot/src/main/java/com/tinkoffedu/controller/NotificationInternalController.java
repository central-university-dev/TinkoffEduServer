package com.tinkoffedu.controller;

import com.tinkoffedu.dto.notification.MeetingNoteNotification;
import com.tinkoffedu.dto.notification.MeetingNotification;
import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.internal.NotificationInternalApi;
import com.tinkoffedu.service.bot.MenteeBotService;
import com.tinkoffedu.service.notification.AfterMeetingNotificationService;
import com.tinkoffedu.service.notification.MeetingNotificationService;
import com.tinkoffedu.service.notification.PreMeetingMenteeNotificationService;
import com.tinkoffedu.service.notification.PreMeetingMentorNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;

@Primary
@RestController
@RequiredArgsConstructor
public class NotificationInternalController implements NotificationInternalApi {

    private final MenteeBotService botService;

    private final MeetingNotificationService meetingNotificationService;
    private final PreMeetingMenteeNotificationService preMeetingMenteeNotificationService;
    private final PreMeetingMentorNotificationService preMeetingMentorNotificationService;
    private final AfterMeetingNotificationService afterMeetingNotificationService;

    @Override
    public StatusResponse sendPreMeetingNoteToMentee(MeetingNoteNotification noteInfo) {
        botService.sendMessage(preMeetingMenteeNotificationService.getMessage(noteInfo));
        return new StatusResponse("ok", null);
    }

    @Override
    public StatusResponse sendPreMeetingNoteToMentor(MeetingNoteNotification noteInfo) {
        botService.sendMessage(preMeetingMentorNotificationService.getMessage(noteInfo));
        return new StatusResponse("ok", null);
    }

    @Override
    public StatusResponse sendMeetingNote(MeetingNoteNotification noteInfo) {
        botService.sendMessage(afterMeetingNotificationService.getMessage(noteInfo));
        return new StatusResponse("ok", null);
    }

    @Override
    public StatusResponse sendMeeting(MeetingNotification meetingInfo) {
        botService.sendMessage(meetingNotificationService.getMessage(meetingInfo));
        return new StatusResponse("ok", null);
    }
}
