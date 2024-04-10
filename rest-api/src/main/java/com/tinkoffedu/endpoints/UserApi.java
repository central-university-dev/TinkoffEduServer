package com.tinkoffedu.endpoints;

import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "users")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "request processed successfully"),
    @ApiResponse(responseCode = "400", description = "validation error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))}),
    @ApiResponse(responseCode = "500", description = "internal error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))})
})
public interface UserApi {

    @Operation(summary = "Создать пользователя")
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    StatusResponse createUser(@RequestBody UserRequest dto);

    @Operation(summary = "Получить пользователя")
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    UserResponse getUser();

    @Operation(summary = "Обновить пользователя")
    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    StatusResponse updateUser(@RequestBody UserRequest dto);

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    StatusResponse deleteUser();
}
