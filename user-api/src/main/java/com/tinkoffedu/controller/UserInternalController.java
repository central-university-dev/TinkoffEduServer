package com.tinkoffedu.controller;

import static com.tinkoffedu.utils.DataEncryptUtils.decrypt;

import com.tinkoffedu.dto.internal.UserTelegramRequest;
import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.internal.UserInternalApi;
import com.tinkoffedu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserInternalController implements UserInternalApi {

    @Value("${data.encrypt.secret-key}")
    private String secretKey;
    private final UserService userService;

    @Override
    public StatusResponse addUserTelegram(UserTelegramRequest dto) {
        try {
            var id = (long) decrypt(dto.userToken(), secretKey).get("id");
            userService.addUserTelegramId(id, dto.telegramUserId());
        } catch (Exception e) {
            return new StatusResponse("Unexpected exception while assigning telegram id to user", e.getMessage());
        }
        return new StatusResponse("ok", null);
    }
}
