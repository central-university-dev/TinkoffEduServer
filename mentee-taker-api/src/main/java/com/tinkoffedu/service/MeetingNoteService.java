package com.tinkoffedu.service;

import com.tinkoffedu.dto.note.MeetingNoteRequest;
import com.tinkoffedu.dto.note.MeetingNoteResponse;

public interface MeetingNoteService {

    boolean canProcessNote(boolean isMentorNote);

    void saveMeetingNote(Long noteId, MeetingNoteRequest dto) throws IllegalAccessException;

    MeetingNoteResponse getMeetingNote(Long noteId);
}
