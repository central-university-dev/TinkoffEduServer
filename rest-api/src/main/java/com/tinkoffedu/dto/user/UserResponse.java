package com.tinkoffedu.dto.user;

public record UserResponse(
    String email,
    String firstName,
    String lastName,
    String phone,
    String group
) {

}
