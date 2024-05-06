package com.tinkoffedu.service;

import com.tinkoffedu.dto.meeting.MeetingResponse;
import com.tinkoffedu.dto.meeting.MeetingsCancelRequest;
import com.tinkoffedu.dto.meeting.MeetingsCreateRequest;
import com.tinkoffedu.dto.meeting.MeetingsRequest;
import com.tinkoffedu.entity.Meeting;
import com.tinkoffedu.entity.MenteeMeetingNote;
import com.tinkoffedu.entity.MentorMeetingNote;
import com.tinkoffedu.entity.MentorMentee;
import com.tinkoffedu.entity.PreMeetingNote;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.mapper.MeetingMapper;
import com.tinkoffedu.repository.MeetingRepository;
import com.tinkoffedu.repository.MentorMenteeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository repository;
    private final MeetingMapper mapper;
    private final MentorMenteeRepository mentorMenteeRepository;

    @Transactional
    public void createMeetings(MeetingsCreateRequest dto) {
        var mentorMentee = mentorMenteeRepository.findByMentorIdAndMenteeId(dto.mentorId(), dto.menteeId()).orElseThrow(
            () -> new NotFoundException(MentorMentee.class)
        );
        dto.meetingDates().forEach(
            date -> repository.save(createMeeting(mentorMentee, date))
        );
    }

    @Transactional
    public void cancelMeetings(MeetingsCancelRequest dto) {
        dto.ids().forEach(id -> repository.findById(id).ifPresent(repository::delete));
    }

    @Transactional(readOnly = true)
    public List<MeetingResponse> getMeetings(MeetingsRequest dto) {
        var mentorMentee = mentorMenteeRepository.findByMentorIdAndMenteeId(dto.mentorId(), dto.menteeId()).orElseThrow(
            () -> new NotFoundException(MentorMentee.class)
        );
        return mentorMentee.getMeetings().stream().map(
            meeting -> mapper.map(meeting, mentorMentee.getMentorName(), mentorMentee.getMenteeName())
        ).toList();
    }

    private Meeting createMeeting(MentorMentee mentorMentee, Instant date) {
        var preMeetingNote = new PreMeetingNote()
            .setMentorMentee(mentorMentee)
            .setMenteeSendDate(date.minus(2, ChronoUnit.DAYS))
            .setIsMenteeSent(false)
            .setMentorSendDate(date.minus(1, ChronoUnit.DAYS))
            .setIsMentorSent(false);

        var mentorMeetingNote = new MentorMeetingNote()
            .setMentorMentee(mentorMentee)
            .setSendDate(date.plus(1, ChronoUnit.HOURS))
            .setIsSent(false);

        var menteeMeetingNote = new MenteeMeetingNote()
            .setMentorMentee(mentorMentee)
            .setSendDate(date.plus(1, ChronoUnit.HOURS))
            .setIsSent(false);

        return new Meeting()
            .setMentorMentee(mentorMentee)
            .setPreMeetingNote(preMeetingNote)
            .setMentorMeetingNote(mentorMeetingNote)
            .setMenteeMeetingNote(menteeMeetingNote)
            .setMeetingDate(date)
            .setMentorMentee(mentorMentee);
    }

}
