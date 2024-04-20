package com.tinkoffedu.entity;

import com.tinkoffedu.entity.question.Question;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "quiz")
@SequenceGenerator(allocationSize = 1, name = "quiz_seq", sequenceName = "quiz_seq")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "questions", columnDefinition = "jsonb")
    @Type(JsonType.class)
    private List<Question> questions;

}
