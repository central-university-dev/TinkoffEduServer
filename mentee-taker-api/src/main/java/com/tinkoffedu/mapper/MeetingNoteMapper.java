package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.note.MeetingNoteRequest;
import com.tinkoffedu.dto.note.MeetingNoteResponse;
import com.tinkoffedu.dto.notes.MenteeMeeting;
import com.tinkoffedu.dto.notes.MentorMeeting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapStructConfig.class)
public interface MeetingNoteMapper {

    @Mapping(target = "general.mood", source = "mood")
    @Mapping(target = "general.weekBusyness", source = "weekBusyness")
    @Mapping(target = "general.weekDifficulty", source = "weekDifficulty")
    @Mapping(target = "indicators.communication", source = "communication")
    @Mapping(target = "indicators.problemDifficulty", source = "problemDifficulty")
    @Mapping(target = "indicators.informationAwareness", source = "informationAwareness")
    @Mapping(target = "indicators.learningEffort", source = "learningEffort")
    @Mapping(target = "notes.message", source = "message")
    @Mapping(target = "notes.links", source = "links")
    MentorMeeting mapMentorNote(MeetingNoteRequest dto);

    @Mapping(target = "mood", source = "general.mood")
    @Mapping(target = "weekBusyness", source = "general.weekBusyness")
    @Mapping(target = "weekDifficulty", source = "general.weekDifficulty")
    @Mapping(target = "communication", source = "indicators.communication")
    @Mapping(target = "problemDifficulty", source = "indicators.problemDifficulty")
    @Mapping(target = "informationAwareness", source = "indicators.informationAwareness")
    @Mapping(target = "learningEffort", source = "indicators.learningEffort")
    @Mapping(target = "message", source = "notes.message")
    @Mapping(target = "links", source = "notes.links")
    @Mapping(target = "isMentor", constant = "true")
    @Mapping(target = "problemSolving", ignore = true)
    @Mapping(target = "clarity", ignore = true)
    MeetingNoteResponse mapMentorNote(MentorMeeting meeting);

    @Mapping(target = "general.mood", source = "mood")
    @Mapping(target = "general.weekBusyness", source = "weekBusyness")
    @Mapping(target = "general.weekDifficulty", source = "weekDifficulty")
    @Mapping(target = "indicators.communication", source = "communication")
    @Mapping(target = "indicators.problemSolving", source = "problemDifficulty")
    @Mapping(target = "indicators.clarity", source = "informationAwareness")
    @Mapping(target = "notes.message", source = "message")
    @Mapping(target = "notes.links", source = "links")
    MenteeMeeting mapMenteeNote(MeetingNoteRequest dto);

    @Mapping(target = "mood", source = "general.mood")
    @Mapping(target = "weekBusyness", source = "general.weekBusyness")
    @Mapping(target = "weekDifficulty", source = "general.weekDifficulty")
    @Mapping(target = "communication", source = "indicators.communication")
    @Mapping(target = "problemSolving", source = "indicators.problemSolving")
    @Mapping(target = "clarity", source = "indicators.clarity")
    @Mapping(target = "message", source = "notes.message")
    @Mapping(target = "links", source = "notes.links")
    @Mapping(target = "isMentor", constant = "false")
    @Mapping(target = "problemDifficulty", ignore = true)
    @Mapping(target = "informationAwareness", ignore = true)
    @Mapping(target = "learningEffort", ignore = true)
    MeetingNoteResponse mapMenteeNote(MenteeMeeting meeting);

}
