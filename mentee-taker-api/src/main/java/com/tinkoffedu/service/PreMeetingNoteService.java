package com.tinkoffedu.service;

import com.tinkoffedu.auth.UserAuthProviderService;
import com.tinkoffedu.dto.note.PreMeetingNoteRequest;
import com.tinkoffedu.dto.note.PreMeetingNoteResponse;
import com.tinkoffedu.dto.notification.MeetingNoteNotification;
import com.tinkoffedu.entity.PreMeetingNote;
import com.tinkoffedu.exception.AccessExpirationException;
import com.tinkoffedu.exception.IllegalSourceAccessException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.mapper.PreMeetingNoteMapper;
import com.tinkoffedu.repository.PreMeetingNoteRepository;
import com.tinkoffedu.utils.DataEncryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PreMeetingNoteService {

    @Value("${data.encrypt.secret-key}")
    private String secretKey;

    private final UserAuthProviderService authProviderService;
    private final PreMeetingNoteRepository repository;
    private final PreMeetingNoteMapper mapper;

    @Transactional
    public void savePreMeetingNote(Long noteId, PreMeetingNoteRequest dto) {
        var note = repository.findById(noteId).orElseThrow(
            () -> new NotFoundException(PreMeetingNote.class)
        );
        if (!Objects.equals(authProviderService.getCurrentUserId(), note.getMentorMentee().getMenteeId())) {
            throw new IllegalSourceAccessException("mentee is trying to access someone else's note");
        }
        if (note.getMenteeSendDate().plus(1, ChronoUnit.DAYS).isBefore(Instant.now())) {
            throw new AccessExpirationException("it's too late to fill out the note");
        }
        repository.save(note.setNotes(mapper.mapPreMeetingNote(dto)));
    }

    @Transactional(readOnly = true)
    public PreMeetingNoteResponse getPreMeetingNote(Long id) {
        var note = repository.findById(id).orElseThrow(
            () -> new NotFoundException(PreMeetingNote.class)
        );
        return mapper.mapPreMeetingNote(note.getNotes());
    }

    @Transactional
    public List<MeetingNoteNotification> getMenteePreMeetingNotesToSend() {
        var notes = repository.findMenteePreMeetingNoteToSend(Instant.now()).stream()
            .map(note -> repository.save(note.setIsMenteeSent(true)))
            .toList();
        return notes.stream()
            .map(note -> new MeetingNoteNotification(
                note.getMentorMentee().getMenteeChatId(), encryptNote(note, false)
            ))
            .toList();
    }

    @Transactional
    public List<MeetingNoteNotification> getMentorPreMeetingNotesToSend() {
        var notes = repository.findMentorPreMeetingNoteToSend(Instant.now()).stream()
            .map(note -> repository.save(note.setIsMenteeSent(true)))
            .toList();
        return notes.stream()
            .map(note -> new MeetingNoteNotification(
                note.getMentorMentee().getMentorChatId(), encryptNote(note, true)
            ))
            .toList();
    }

    private String encryptNote(PreMeetingNote note, Boolean isMentorNote) {
        return DataEncryptUtils.encrypt(
            Map.of(
                "id", note.getId(),
                "isMentorNote", isMentorNote
            ),
            secretKey
        );
    }

}
