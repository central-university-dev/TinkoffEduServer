package com.tinkoffedu.endpoints;

import com.tinkoffedu.dto.note.PreMeetingNoteRequest;
import com.tinkoffedu.dto.note.PreMeetingNoteResponse;
import com.tinkoffedu.dto.status.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "pre-meeting-note")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "request processed successfully"),
    @ApiResponse(responseCode = "400", description = "validation error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))}),
    @ApiResponse(responseCode = "500", description = "internal error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))})
})
public interface PreMeetingNoteApi {

    @Operation(summary = "Получить premeeting note")
    @GetMapping(value = "/api/premeeting-note/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    PreMeetingNoteResponse getPreMeetingNote(@PathVariable("noteId") String noteId);

    @Operation(summary = "Сохранить premeeting note")
    @PostMapping(value = "/api/premeeting-note/{noteId}/save", produces = MediaType.APPLICATION_JSON_VALUE)
    StatusResponse savePreMeetingNote(@PathVariable("noteId") String noteId, @RequestBody PreMeetingNoteRequest dto);

}
