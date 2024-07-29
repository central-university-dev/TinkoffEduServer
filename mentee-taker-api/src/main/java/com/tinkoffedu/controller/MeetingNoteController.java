package com.tinkoffedu.controller;

import static com.tinkoffedu.utils.DataEncryptUtils.decrypt;

import com.tinkoffedu.dto.internal.UserTelegramBindResponse;
import com.tinkoffedu.dto.note.MeetingNoteRequest;
import com.tinkoffedu.dto.note.MeetingNoteResponse;
import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.endpoints.MeetingNoteApi;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.service.MeetingNoteService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeetingNoteController implements MeetingNoteApi {

    @Value("${data.encrypt.secret-key}")
    private String secretKey;

    private final List<MeetingNoteService> meetingNoteServices;

    @Override
    public MeetingNoteResponse getMeetingNote(String noteId) {
        try {
            var note = getNote(noteId);
            return meetingNoteServices.stream()
                .filter(service -> service.canProcessNote(note.get("isMentorNote", Boolean.class)))
                .findFirst()
                .get()
                .getMeetingNote(note.get("id", Long.class));
        } catch (Exception e) {
            throw new InvalidArgumentException("Meeting note link is incorrect. " + e.getMessage());
        }
    }

    @Override
    public StatusResponse saveMeetingNote(String noteId, MeetingNoteRequest dto) {
        try {
            var note = getNote(noteId);
            meetingNoteServices.stream()
                .filter(service -> service.canProcessNote(note.get("isMentorNote", Boolean.class)))
                .findFirst()
                .get()
                .saveMeetingNote(note.get("id", Long.class), dto);
            return new StatusResponse("ok", null);
        } catch (Exception e) {
            return new StatusResponse("error", e.getMessage());
        }
    }

    private Claims getNote(String noteId) {
        return decrypt(noteId, secretKey);
    }
}
