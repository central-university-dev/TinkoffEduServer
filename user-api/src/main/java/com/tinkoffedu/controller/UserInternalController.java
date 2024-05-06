package com.tinkoffedu.controller;

import static com.tinkoffedu.utils.DataEncryptUtils.decrypt;

import com.tinkoffedu.dto.internal.UserTelegramBindRequest;
import com.tinkoffedu.dto.internal.UserTelegramBindResponse;
import com.tinkoffedu.internal.UserInternalApi;
import com.tinkoffedu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;

@Primary
@RestController
@RequiredArgsConstructor
public class UserInternalController implements UserInternalApi {

    @Value("${data.encrypt.secret-key}")
    private String secretKey;
    private final UserService userService;

    @Override
    public UserTelegramBindResponse addUserTelegram(UserTelegramBindRequest dto) {
        try {
            var id = decrypt(dto.userToken(), secretKey).get("id", Long.class);
            return userService.addUserTelegramId(id, dto.telegramChatId());
        } catch (Exception e) {
            return new UserTelegramBindResponse(null, null,
                "Unexpected exception while assigning telegram id to user: %s".formatted(e.getMessage())
            );
        }
    }

}
