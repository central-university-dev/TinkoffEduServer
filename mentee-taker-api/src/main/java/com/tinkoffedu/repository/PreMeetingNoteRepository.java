package com.tinkoffedu.repository;

import com.tinkoffedu.entity.PreMeetingNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface PreMeetingNoteRepository extends JpaRepository<PreMeetingNote, Long> {

    @Query("""
        SELECT p
        FROM PreMeetingNote p
        WHERE NOT p.isMenteeSent
          AND p.menteeSendDate >= :currentDate
        """)
    List<PreMeetingNote> findMenteePreMeetingNoteToSend(@Param("currentDate") Instant currentDate);

    @Query("""
        SELECT p
        FROM PreMeetingNote p
        WHERE NOT p.isMentorSent
          AND p.mentorSendDate >= :currentDate
        """)
    List<PreMeetingNote> findMentorPreMeetingNoteToSend(@Param("currentDate") Instant currentDate);
}
