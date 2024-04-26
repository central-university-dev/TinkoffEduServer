package com.tinkoffedu.internal;

import com.tinkoffedu.dto.internal.UserTelegramBindRequest;
import com.tinkoffedu.dto.internal.UserTelegramBindResponse;
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

@Tag(name = "internal-users")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "request processed successfully"),
    @ApiResponse(responseCode = "400", description = "validation error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))}),
    @ApiResponse(responseCode = "500", description = "internal error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))})
})
public interface UserInternalApi {

    @Operation(summary = "Добавить id пользователя в телеграмме")
    @PostMapping(
        value = "/internal/user/add-user-telegram",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserTelegramBindResponse addUserTelegram(@RequestBody UserTelegramBindRequest dto);

}
