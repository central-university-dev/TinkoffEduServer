package com.tinkoffedu.service;

import com.tinkoffedu.entity.User;
import com.tinkoffedu.entity.UserRefreshToken;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.exception.RefreshTokenException;
import com.tinkoffedu.repository.UserRefreshTokenRepository;
import com.tinkoffedu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRefreshTokenService {

    private final UserRepository userRepository;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Value("${security.jwt.expiration-time}")
    private Long expirationMinutes;
    @Value("${security.jwt.refresh-period}")
    private Long refreshMinutes;

    @Transactional
    public UserRefreshToken createRefreshToken(Long userId) {
        return userRefreshTokenRepository.save(
            new UserRefreshToken()
                .setUser(userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class)))
                .setRefreshToken(UUID.randomUUID().toString())
                .setExpirationDate(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(expirationMinutes)))
        );
    }

    @Transactional
    public UserRefreshToken updateRefreshToken(String token) {
        UserRefreshToken refreshToken = userRefreshTokenRepository.findByRefreshToken(token).orElseThrow(
            () -> new RefreshTokenException("Refresh token not found or expired")
        );
        if (refreshToken.isExpired()) {
            userRefreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Refresh token is expired");
        }
        Instant newExpirationDate = Instant.now().plusMillis(
            TimeUnit.MINUTES.toMillis(expirationMinutes + refreshMinutes)
        );
        String newRefreshToken = UUID.randomUUID().toString();
        return userRefreshTokenRepository.save(
            refreshToken
                .setRefreshToken(newRefreshToken)
                .setExpirationDate(newExpirationDate)
        );
    }

    @Transactional
    public void clearExpiredTokens() {
        userRefreshTokenRepository.deleteAllByExpirationDateBefore(Instant.now());
    }

}
