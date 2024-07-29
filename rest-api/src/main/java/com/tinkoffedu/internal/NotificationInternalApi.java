package com.tinkoffedu.internal;

import com.tinkoffedu.dto.notification.MeetingNoteNotification;
import com.tinkoffedu.dto.notification.MeetingNotification;
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

@Tag(name = "internal-notifications")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "request processed successfully"),
    @ApiResponse(responseCode = "400", description = "validation error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))}),
    @ApiResponse(responseCode = "500", description = "internal error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))})
})
public interface NotificationInternalApi {

    @Operation(summary = "Отправить менти форму перед встречей")
    @PostMapping(
        value = "/internal/notification/premeeting-note-mentee",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    StatusResponse sendPreMeetingNoteToMentee(@RequestBody MeetingNoteNotification noteInfo);

    @Operation(summary = "Отправить ментору форму перед встречей")
    @PostMapping(
        value = "/internal/notification/premeeting-note-mentor",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    StatusResponse sendPreMeetingNoteToMentor(@RequestBody MeetingNoteNotification noteInfo);

    @Operation(summary = "Отправить форму после встречей")
    @PostMapping(
        value = "/internal/notification/meeting-mentee",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    StatusResponse sendMeetingNote(@RequestBody MeetingNoteNotification noteInfo);

    @Operation(summary = "Отправить уведомление о встрече")
    @PostMapping(
        value = "/internal/notification/meeting",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    StatusResponse sendMeeting(@RequestBody MeetingNotification meetingInfo);

}
