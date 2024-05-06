package com.tinkoffedu.service;

import com.tinkoffedu.dto.note.MentorMeetingNoteRequest;
import com.tinkoffedu.entity.MentorMeetingNote;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.repository.MentorMeetingNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MentorMeetingNoteService {

    private final MentorMeetingNoteRepository repository;
    // private final MentorMeetingNoteMapper mapper;

    @Transactional
    public void saveMentorMeetingNote(MentorMeetingNoteRequest dto) {
        var note = repository.findById(dto.id()).orElseThrow(
            () -> new NotFoundException(MentorMeetingNote.class)
        );
        // TODO: доделать
        // repository.save(mapper.dto())
    }

}
