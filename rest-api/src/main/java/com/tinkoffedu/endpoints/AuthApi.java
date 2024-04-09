package com.tinkoffedu.endpoints;

import com.tinkoffedu.dto.auth.AuthRequest;
import com.tinkoffedu.dto.auth.AuthResponse;
import com.tinkoffedu.dto.auth.RefreshTokenRequest;
import com.tinkoffedu.dto.auth.RefreshTokenResponse;
import com.tinkoffedu.dto.status.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "authentication")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "request processed successfully"),
    @ApiResponse(responseCode = "400", description = "validation error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))}),
    @ApiResponse(responseCode = "500", description = "internal error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))})
})
public interface AuthApi {

    @Operation(summary = "Логин по email и паролю")
    @PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    AuthResponse login(@RequestBody AuthRequest request);

    @Operation(summary = "Обновление токена авторизации")
    @PostMapping(value = "/auth/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest request);
}
