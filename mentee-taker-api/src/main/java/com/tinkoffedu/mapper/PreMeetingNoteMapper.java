package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.note.MeetingNoteRequest;
import com.tinkoffedu.dto.note.MeetingNoteResponse;
import com.tinkoffedu.dto.note.PreMeetingNoteRequest;
import com.tinkoffedu.dto.note.PreMeetingNoteResponse;
import com.tinkoffedu.dto.notes.MenteeMeeting;
import com.tinkoffedu.dto.notes.MentorMeeting;
import com.tinkoffedu.dto.notes.PreMeeting;
import com.tinkoffedu.entity.PreMeetingNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapStructConfig.class)
public interface PreMeetingNoteMapper {

    @Mapping(target = "general.mood", source = "mood")
    @Mapping(target = "general.weekBusyness", source = "weekBusyness")
    @Mapping(target = "general.weekDifficulty", source = "weekDifficulty")
    @Mapping(target = "notes.message", source = "message")
    @Mapping(target = "notes.links", source = "links")
    PreMeeting mapPreMeetingNote(PreMeetingNoteRequest dto);

    @Mapping(target = "mood", source = "general.mood")
    @Mapping(target = "weekBusyness", source = "general.weekBusyness")
    @Mapping(target = "weekDifficulty", source = "general.weekDifficulty")
    @Mapping(target = "message", source = "notes.message")
    @Mapping(target = "links", source = "notes.links")
    PreMeetingNoteResponse mapPreMeetingNote(PreMeeting premeeting);

}
