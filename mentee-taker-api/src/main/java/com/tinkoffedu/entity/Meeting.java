package com.tinkoffedu.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "mentor_mentee_meeting")
@SequenceGenerator(allocationSize = 1, name = "mentor_mentee_meeting_seq", sequenceName = "mentor_mentee_meetings_seq")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_mentee_meeting_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "meeting_date", nullable = false)
    private Instant meetingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_mentee_id", referencedColumnName = "id")
    private MentorMentee mentorMentee;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pre_meeting_note_id", referencedColumnName = "id")
    private PreMeetingNote preMeetingNote;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mentor_meeting_note_id", referencedColumnName = "id")
    private MentorMeetingNote mentorMeetingNote;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mentee_meeting_note_id", referencedColumnName = "id")
    private MenteeMeetingNote menteeMeetingNote;

}
