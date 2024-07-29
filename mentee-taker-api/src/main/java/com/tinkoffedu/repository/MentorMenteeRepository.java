package com.tinkoffedu.repository;

import com.tinkoffedu.entity.MentorMentee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MentorMenteeRepository extends JpaRepository<MentorMentee, Long> {

    Optional<MentorMentee> findByMentorIdAndMenteeId(Long mentorId, Long menteeId);

    List<MentorMentee> findAllByMentorId(Long mentorId);

}
