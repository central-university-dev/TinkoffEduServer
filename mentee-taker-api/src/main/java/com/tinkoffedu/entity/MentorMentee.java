package com.tinkoffedu.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "mentor_mentee")
@SequenceGenerator(allocationSize = 1, name = "mentor_mentee_seq", sequenceName = "mentor_mentee_seq")
public class MentorMentee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_mentee_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "mentor_id", nullable = false)
    private Long mentorId;

    @Column(name = "mentee_id", nullable = false)
    private Long menteeId;

    @Column(name = "mentor_chat_id", nullable = false)
    private Long mentorChatId;

    @Column(name = "mentee_chat_id", nullable = false)
    private Long menteeChatId;

    @Column(name = "mentor_name", nullable = false)
    private String mentorName;

    @Column(name = "mentee_name", nullable = false)
    private String menteeName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mentorMentee", cascade = CascadeType.REMOVE)
    private List<Meeting> meetings = new ArrayList<>();

}
