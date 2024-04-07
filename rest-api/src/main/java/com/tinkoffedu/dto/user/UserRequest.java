package com.tinkoffedu.dto.user;

public record UserRequest(
    String email,
    String password,
    String firstName,
    String lastName,
    String phone
) {

}
