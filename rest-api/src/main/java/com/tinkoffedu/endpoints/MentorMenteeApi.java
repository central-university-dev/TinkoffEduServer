package com.tinkoffedu.endpoints;

import com.tinkoffedu.dto.mentee.MentorMenteeRequest;
import com.tinkoffedu.dto.mentee.MentorMenteeResponse;
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

import java.util.List;

@Tag(name = "mentor-mentee")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "request processed successfully"),
    @ApiResponse(responseCode = "400", description = "validation error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))}),
    @ApiResponse(responseCode = "500", description = "internal error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))})
})
public interface MentorMenteeApi {

    @Operation(summary = "Создать пару ментор-менти")
    @PostMapping(value = "/api/mentor-mentee/create", produces = MediaType.APPLICATION_JSON_VALUE)
    MentorMenteeResponse createMentorMentee(@RequestBody MentorMenteeRequest dto);

    @Operation(summary = "Получить всех менти ментора")
    @GetMapping(value = "/api/mentor-mentee/{mentorId}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MentorMenteeResponse> getMentorMentees(@PathVariable("mentorId") Long mentorId);
}
