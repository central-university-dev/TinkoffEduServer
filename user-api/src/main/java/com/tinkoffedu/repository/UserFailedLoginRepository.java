package com.tinkoffedu.repository;

import com.tinkoffedu.entity.UserFailedLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserFailedLoginRepository extends JpaRepository<UserFailedLogin, Long> {

    Optional<Long> countByUserIdAndDateGreaterThanEqual(Long userId, LocalDateTime failedLoginStart);

}
