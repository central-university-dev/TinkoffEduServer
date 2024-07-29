package com.tinkoffedu.service;

import com.tinkoffedu.auth.UserAuthProviderService;
import com.tinkoffedu.dto.note.MeetingNoteRequest;
import com.tinkoffedu.dto.note.MeetingNoteResponse;
import com.tinkoffedu.entity.MentorMeetingNote;
import com.tinkoffedu.exception.AccessExpirationException;
import com.tinkoffedu.exception.IllegalSourceAccessException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.mapper.MeetingNoteMapper;
import com.tinkoffedu.repository.MentorMeetingNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MentorMeetingNoteService implements MeetingNoteService {

    private final UserAuthProviderService authProviderService;
    private final MentorMeetingNoteRepository repository;
    private final MeetingNoteMapper mapper;

    @Override
    public boolean canProcessNote(boolean isMentorNote) {
        return isMentorNote;
    }

    @Override
    @Transactional
    public void saveMeetingNote(Long noteId, MeetingNoteRequest dto) {
        var note = repository.findById(noteId).orElseThrow(
            () -> new NotFoundException(MentorMeetingNote.class)
        );
        if (!Objects.equals(authProviderService.getCurrentUserId(), note.getMentorMentee().getMentorId())) {
            throw new IllegalSourceAccessException("mentor is trying to access someone else's note");
        }
        if (note.getSendDate().plus(1, ChronoUnit.DAYS).isBefore(Instant.now())) {
            throw new AccessExpirationException("it's too late to fill out the note");
        }
        repository.save(note.setNotes(mapper.mapMentorNote(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public MeetingNoteResponse getMeetingNote(Long noteId) {
        var note = repository.findById(noteId).orElseThrow(
            () -> new NotFoundException(MentorMeetingNote.class)
        );
        return mapper.mapMentorNote(note.getNotes());
    }
}
