package com.tinkoffedu.controller;

import com.tinkoffedu.dto.mentee.MentorMenteeRequest;
import com.tinkoffedu.dto.mentee.MentorMenteeResponse;
import com.tinkoffedu.endpoints.MentorMenteeApi;
import com.tinkoffedu.service.MentorMenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MentorMenteeController implements MentorMenteeApi {

    private final MentorMenteeService service;

    @Override
    public MentorMenteeResponse createMentorMentee(MentorMenteeRequest dto) {
        return service.createMentorMentee(dto);
    }

    @Override
    public List<MentorMenteeResponse> getMentorMentees(Long mentorId) {
        return service.getMentorMentees(mentorId);
    }
}
