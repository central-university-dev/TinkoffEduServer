package com.tinkoffedu.dto.role;

import java.util.List;

public record UserRoleResponse(
    String email,
    String firstName,
    String lastName,
    List<String> roles
) {

}
