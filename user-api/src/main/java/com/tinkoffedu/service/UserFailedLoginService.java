package com.tinkoffedu.service;

import com.tinkoffedu.dto.UserAuthDetails;
import com.tinkoffedu.entity.UserFailedLogin;
import com.tinkoffedu.repository.UserFailedLoginRepository;
import com.tinkoffedu.security.UserAuthDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFailedLoginService {

    private final UserFailedLoginRepository userFailedLoginRepository;
    private final UserAuthDetailsService userAuthDetailsService;

    public void logFailedUserAttempt(String email) {
        try {
            var userDetails = (UserAuthDetails) userAuthDetailsService.loadUserByUsername(email);
            var userLog = new UserFailedLogin()
                .setUserId(userDetails.getId())
                .setDate(LocalDateTime.now());
            userFailedLoginRepository.save(userLog);
        } catch (UsernameNotFoundException e) {
            log.error("User with email {} not found", email);
        }
    }

    public long getUserFailedLoginAttempts(Long userId, LocalDateTime timestamp) {
        return userFailedLoginRepository.countByUserIdAndDateGreaterThanEqual(userId, timestamp).orElse(0L);
    }

}
