package com.tinkoffedu.service;

import com.tinkoffedu.dto.mentee.MentorMenteeRequest;
import com.tinkoffedu.dto.mentee.MentorMenteeResponse;
import com.tinkoffedu.entity.MentorMentee;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.mapper.MentorMenteeMapper;
import com.tinkoffedu.repository.MentorMenteeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorMenteeService {

    private final MentorMenteeRepository repository;
    private final MentorMenteeMapper mapper;

    @Transactional
    public MentorMenteeResponse createMentorMentee(MentorMenteeRequest dto) {
        repository.findByMentorIdAndMenteeId(dto.mentorId(), dto.menteeId()).ifPresent(
            mentorMentee -> {
                throw new InvalidArgumentException("Mentor-Mentee already exists");
            }
        );
        return mapper.map(repository.save(mapper.map(dto)));
    }

    @Transactional(readOnly = true)
    public List<MentorMenteeResponse> getMentorMentees(Long mentorId) {
        return mapper.map(repository.findAllByMentorId(mentorId));
    }

}
