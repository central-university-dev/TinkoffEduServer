package com.tinkoffedu.controller;

import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.dto.user.UserResponse;
import com.tinkoffedu.endpoints.UserApi;
import com.tinkoffedu.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public StatusResponse createUser(UserRequest dto) {
        service.createUser(dto);
        return new StatusResponse("ok", null);
    }

    @Override
    public UserResponse getUser(Long id) {
        return service.getUser(id);
    }

    @Override
    public StatusResponse updateUser(Long id, UserRequest dto) {
        service.updateUser(id, dto);
        return new StatusResponse("ok", null);
    }

    @Override
    public StatusResponse deleteUser(Long id) {
        service.deleteUser(id);
        return new StatusResponse("ok", null);
    }

}
