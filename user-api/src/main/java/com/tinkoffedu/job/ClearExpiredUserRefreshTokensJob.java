package com.tinkoffedu.job;

import com.tinkoffedu.service.UserRefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClearExpiredUserRefreshTokensJob {

    private static final String LOCK_NAME = "clearExpiredUserRefreshTokens";

    private final UserRefreshTokenService userRefreshTokenService;

    @Scheduled(cron = "${job.clear-expired-tokens.cron}")
    @SchedulerLock(name = LOCK_NAME, lockAtMostFor = "${job.clear-expired-tokens.lock-at-most-for}")
    public void sendSalesByCron() {
        log.debug("Lock {} was successfully acquired. Trying to clear expired tokens", LOCK_NAME);
        userRefreshTokenService.clearExpiredTokens();
        log.debug("Successfully released lock {}", LOCK_NAME);
    }

}
