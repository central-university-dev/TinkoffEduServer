package com.tinkoffedu.security;

import com.tinkoffedu.config.BruteforceConfig;
import com.tinkoffedu.dto.UserAuthDetails;
import com.tinkoffedu.service.UserFailedLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserAuthAttemptChecker implements UserDetailsChecker {

    private final UserFailedLoginService failedLoginLogService;
    private final BruteforceConfig bruteforceConfig;

    @Override
    public void check(UserDetails toCheck) {
        if (!bruteforceConfig.getEnabled()) {
            return;
        }
        var failedAttemptsCount = failedLoginLogService.getUserFailedLoginAttempts(
            ((UserAuthDetails) toCheck).getId(),
            LocalDateTime.now().minusMinutes(bruteforceConfig.getPeriod())
        );
        if (bruteforceConfig.getMaxAttempts() - failedAttemptsCount <= 0L) {
            throw new LockedException("Account is locked :(");
        }
    }

}
