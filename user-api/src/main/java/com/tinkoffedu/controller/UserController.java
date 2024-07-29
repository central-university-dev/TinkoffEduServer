package com.tinkoffedu.controller;

import com.tinkoffedu.auth.UserAuthProviderService;
import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.dto.user.UserResponse;
import com.tinkoffedu.endpoints.UserApi;
import com.tinkoffedu.service.UserCreateNotificationService;
import com.tinkoffedu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserAuthProviderService authProviderService;
    private final UserService userService;
    private final UserCreateNotificationService notificationService;

    @Override
    public StatusResponse createUser(UserRequest dto) {
        Long id = userService.createUser(dto);
        notificationService.notify(id, dto);
        return new StatusResponse("ok", null);
    }

    @Override
    public UserResponse getUser() {
        Long id = authProviderService.getCurrentUserId();
        return userService.getUser(id);
    }

    @Override
    public UserResponse updateUser(UserRequest dto) {
        Long id = authProviderService.getCurrentUserId();
        return userService.updateUser(id, dto);
    }

    @Override
    public StatusResponse deleteUser() {
        Long id = authProviderService.getCurrentUserId();
        userService.deleteUser(id);
        return new StatusResponse("ok", null);
    }

}
