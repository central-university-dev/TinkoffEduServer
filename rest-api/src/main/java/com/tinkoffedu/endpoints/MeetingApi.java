package com.tinkoffedu.endpoints;

import com.tinkoffedu.dto.meeting.MeetingsCancelRequest;
import com.tinkoffedu.dto.meeting.MeetingsCreateRequest;
import com.tinkoffedu.dto.meeting.MeetingsRequest;
import com.tinkoffedu.dto.meeting.MeetingsResponse;
import com.tinkoffedu.dto.status.StatusResponse;
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
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "meetings")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "request processed successfully"),
    @ApiResponse(responseCode = "400", description = "validation error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))}),
    @ApiResponse(responseCode = "500", description = "internal error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))})
})
public interface MeetingApi {

    @Operation(summary = "Создать встречу")
    @PostMapping(value = "/api/meetings/create", produces = MediaType.APPLICATION_JSON_VALUE)
    MeetingsResponse createMeetings(@RequestBody MeetingsCreateRequest dto);

    @Operation(summary = "Получить мероприятия")
    @GetMapping(value = "/api/meetings/all", produces = MediaType.APPLICATION_JSON_VALUE)
    MeetingsResponse getMeetings(@RequestBody MeetingsRequest dto);

    @Operation(summary = "Удалить мероприятия")
    @DeleteMapping(value = "/api/meetings/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    StatusResponse deleteMeetings(@RequestBody MeetingsCancelRequest dto);
}
