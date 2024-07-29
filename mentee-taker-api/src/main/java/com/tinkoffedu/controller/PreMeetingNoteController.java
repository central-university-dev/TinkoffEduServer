package com.tinkoffedu.controller;

import static com.tinkoffedu.utils.DataEncryptUtils.decrypt;

import com.tinkoffedu.dto.note.PreMeetingNoteRequest;
import com.tinkoffedu.dto.note.PreMeetingNoteResponse;
import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.endpoints.MeetingNoteApi;
import com.tinkoffedu.endpoints.PreMeetingNoteApi;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.service.MeetingNoteService;
import com.tinkoffedu.service.PreMeetingNoteService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PreMeetingNoteController implements PreMeetingNoteApi {

    @Value("${data.encrypt.secret-key}")
    private String secretKey;

    private final PreMeetingNoteService preMeetingNoteService;

    @Override
    public PreMeetingNoteResponse getPreMeetingNote(String noteId) {
        try {
            var note = getNote(noteId);
            return preMeetingNoteService.getPreMeetingNote(note.get("id", Long.class));
        } catch (Exception e) {
            throw new InvalidArgumentException("Meeting note link is incorrect. " + e.getMessage());
        }
    }

    @Override
    public StatusResponse savePreMeetingNote(String noteId, PreMeetingNoteRequest dto) {
        try {
            var note = getNote(noteId);
            preMeetingNoteService.savePreMeetingNote(note.get("id", Long.class), dto);
            return new StatusResponse("ok", null);
        } catch (Exception e) {
            return new StatusResponse("error", e.getMessage());
        }
    }

    private Claims getNote(String noteId) {
        return decrypt(noteId, secretKey);
    }
}
