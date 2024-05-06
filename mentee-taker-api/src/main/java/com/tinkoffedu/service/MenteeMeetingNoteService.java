package com.tinkoffedu.service;

import com.tinkoffedu.dto.note.MenteeMeetingNoteRequest;
import com.tinkoffedu.entity.MenteeMeetingNote;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.repository.MenteeMeetingNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenteeMeetingNoteService {

    private final MenteeMeetingNoteRepository repository;
    // private final MenteeMeetingNoteMapper mapper;

    @Transactional
    public void saveMenteeMeetingNote(MenteeMeetingNoteRequest dto) {
        var note = repository.findById(dto.id()).orElseThrow(
            () -> new NotFoundException(MenteeMeetingNote.class)
        );
        // TODO: доделать
        // repository.save(mapper.dto())
    }

}
