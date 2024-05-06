package com.tinkoffedu.entity;

import com.tinkoffedu.dto.notes.PreMeeting;
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
@Table(name = "premeeting_note")
@SequenceGenerator(allocationSize = 1, name = "premeeting_note_seq", sequenceName = "premeeting_note_seq")
public class PreMeetingNote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "premeeting_note_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "mentee_send_date", nullable = false)
    private Instant menteeSendDate;

    @Column(name = "mentor_send_date", nullable = false)
    private Instant mentorSendDate;

    @Column(name = "is_mentee_sent", nullable = false)
    private Boolean isMenteeSent;

    @Column(name = "is_mentor_sent", nullable = false)
    private Boolean isMentorSent;

    @Column(name = "notes", columnDefinition = "jsonb")
    @Type(JsonType.class)
    private PreMeeting notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_mentee_id", referencedColumnName = "id")
    private MentorMentee mentorMentee;

}
