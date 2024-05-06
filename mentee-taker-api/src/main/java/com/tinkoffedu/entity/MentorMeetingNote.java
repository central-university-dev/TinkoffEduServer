package com.tinkoffedu.entity;

import com.tinkoffedu.dto.notes.MentorMeeting;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "mentor_meeting_note")
@SequenceGenerator(allocationSize = 1, name = "mentor_meeting_note_seq", sequenceName = "mentor_meeting_note_seq")
public class MentorMeetingNote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_meeting_note_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "send_date", nullable = false)
    private Instant sendDate;

    @Column(name = "is_sent", nullable = false)
    private Boolean isSent;

    @Column(name = "notes", columnDefinition = "jsonb")
    @Type(JsonType.class)
    private MentorMeeting notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_mentee_id", referencedColumnName = "id")
    private MentorMentee mentorMentee;

}
