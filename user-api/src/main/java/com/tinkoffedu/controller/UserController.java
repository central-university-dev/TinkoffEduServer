package com.tinkoffedu.controller;

import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.dto.user.UserResponse;
import com.tinkoffedu.endpoints.UserApi;
import com.tinkoffedu.security.UserAuthProviderService;
import com.tinkoffedu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserAuthProviderService authProviderService;
    private final UserService userService;

    @Override
    public StatusResponse createUser(UserRequest dto) {
        userService.createUser(dto);
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
