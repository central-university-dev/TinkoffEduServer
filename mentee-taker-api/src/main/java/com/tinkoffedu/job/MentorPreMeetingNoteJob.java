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
public class MentorPreMeetingNoteJob {

    private static final String LOCK_NAME = "sendMentorPreMeetingNote";

    private final NotificationInternalApi notificationInternalApiService;
    private final PreMeetingNoteService preMeetingNoteService;

    @Scheduled(cron = "${job.mentor-premeeting.cron}")
    @SchedulerLock(name = LOCK_NAME, lockAtMostFor = "${job.mentor-premeeting.lock-at-most-for}")
    public void sendMentorPreMeetingNote() {
        log.debug("Lock {} was successfully acquired", LOCK_NAME);
        preMeetingNoteService.getMentorPreMeetingNotesToSend().forEach(
            notificationInternalApiService::sendPreMeetingNoteToMentor
        );
        log.debug("Successfully released lock {}", LOCK_NAME);
    }

}
