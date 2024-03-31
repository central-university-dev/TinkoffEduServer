package com.tinkoffedu.controller;

import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.dto.user.UserResponse;
import com.tinkoffedu.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @Operation(summary = "Create user")
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User created"),
        @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public StatusResponse createUser(@RequestBody UserRequest dto) {
        service.createUser(dto);
        return new StatusResponse("ok", null);
    }

    @Operation(summary = "Get user")
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public UserResponse getUser(@PathVariable("id") Long id) {
        return service.getUser(id);
    }

    @Operation(summary = "Update user")
    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public StatusResponse updateUser(@PathVariable("id") Long id, @RequestBody UserRequest dto) {
        service.updateUser(id, dto);
        return new StatusResponse("ok", null);
    }

    @Operation(summary = "Delete user")
    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public StatusResponse deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return new StatusResponse("ok", null);
    }

}
