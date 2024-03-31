package com.tinkoffedu.dto.user;

public record UserRequest(
    Long id,
    String email,
    String password,
    String firstName,
    String lastName,
    String phone
) {

}
