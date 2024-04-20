package com.tinkoffedu.endpoints;

import com.tinkoffedu.dto.event.EventRequest;
import com.tinkoffedu.dto.event.EventResponse;
import com.tinkoffedu.dto.quiz.QuizRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "events")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "request processed successfully"),
    @ApiResponse(responseCode = "400", description = "validation error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))}),
    @ApiResponse(responseCode = "500", description = "internal error", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StatusResponse.class))})
})
public interface EventApi {

    @Operation(summary = "Создать мероприятие")
    @PostMapping(value = "/api/event/create", produces = MediaType.APPLICATION_JSON_VALUE)
    EventResponse createEvent(@RequestBody EventRequest dto);

    @Operation(summary = "Получить мероприятие")
    @GetMapping(value = "/api/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    EventResponse getEvent(@PathVariable("id") Long id);

    @Operation(summary = "Обновить мероприятие")
    @PutMapping(value = "/api/event/{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    EventResponse updateEvent(@PathVariable("id") Long id, @RequestBody EventRequest dto);

    @Operation(summary = "Удалить мероприятие")
    @DeleteMapping(value = "/api/event/{id}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    StatusResponse deleteEvent(@PathVariable("id") Long id);

    @Operation(summary = "Добавить квиз мероприятия")
    @PutMapping(value = "/api/event/{id}/add-quiz", produces = MediaType.APPLICATION_JSON_VALUE)
    EventResponse addEventQuiz(@PathVariable("id") Long id, @RequestBody QuizRequest dto);

    @Operation(summary = "Обновить квиз мероприятия")
    @PutMapping(value = "/api/event/{id}/update-quiz", produces = MediaType.APPLICATION_JSON_VALUE)
    EventResponse updateEventQuiz(@PathVariable("id") Long id, @RequestBody QuizRequest dto);
}
