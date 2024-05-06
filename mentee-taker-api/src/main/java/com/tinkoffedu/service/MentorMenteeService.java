package com.tinkoffedu.service;

import com.tinkoffedu.dto.mentee.MentorMenteeRequest;
import com.tinkoffedu.mapper.MentorMenteeMapper;
import com.tinkoffedu.repository.MentorMenteeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MentorMenteeService {

    private final MentorMenteeRepository repository;
    private final MentorMenteeMapper mapper;

    @Transactional
    public void createMentorMentee(MentorMenteeRequest dto) {
        repository.save(mapper.map(dto));
    }

}
