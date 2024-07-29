package com.tinkoffedu.job;

import com.tinkoffedu.internal.NotificationInternalApi;
import com.tinkoffedu.service.PreMeetingNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenteePreMeetingNoteJob {

    private static final String LOCK_NAME = "sendMenteePreMeetingNote";

    private final NotificationInternalApi notificationInternalApiService;
    private final PreMeetingNoteService preMeetingNoteService;

    @Scheduled(cron = "${job.mentee-premeeting.cron}")
    @SchedulerLock(name = LOCK_NAME, lockAtMostFor = "${job.mentee-premeeting.lock-at-most-for}")
    public void sendMenteePreMeetingNote() {
        log.debug("Lock {} was successfully acquired", LOCK_NAME);
        preMeetingNoteService.getMenteePreMeetingNotesToSend().forEach(
            notificationInternalApiService::sendPreMeetingNoteToMentee
        );
        log.debug("Successfully released lock {}", LOCK_NAME);
    }

}
