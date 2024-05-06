package com.tinkoffedu.service;

import com.tinkoffedu.dto.note.PreMeetingNoteRequest;
import com.tinkoffedu.entity.PreMeetingNote;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.repository.PreMeetingNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PreMeetingNoteService {

    private final PreMeetingNoteRepository repository;

    @Transactional
    public void savePreMeetingNote(PreMeetingNoteRequest dto) {
        var note = repository.findById(dto.id()).orElseThrow(
            () -> new NotFoundException(PreMeetingNote.class)
        );
        // TODO: доделать
        // repository.save(mapper.dto())
    }

    @Transactional(readOnly = true)
    public void getPreMeetingNote(Long id) {
        var note = repository.findById(id).orElseThrow(
            () -> new NotFoundException(PreMeetingNote.class)
        );
    }

}
