package com.tinkoffedu.dto.role;

import java.util.List;

public record RolePermissionResponse(
    String name,
    List<String> permissions
) {

}
