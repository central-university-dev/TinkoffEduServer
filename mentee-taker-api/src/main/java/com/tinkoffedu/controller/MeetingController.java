package com.tinkoffedu.controller;

import com.tinkoffedu.dto.meeting.MeetingResponse;
import com.tinkoffedu.dto.meeting.MeetingsCancelRequest;
import com.tinkoffedu.dto.meeting.MeetingsCreateRequest;
import com.tinkoffedu.dto.meeting.MeetingsRequest;
import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.endpoints.MeetingApi;
import com.tinkoffedu.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeetingController implements MeetingApi {

    private final MeetingService service;

    @Override
    public List<MeetingResponse> createMeetings(MeetingsCreateRequest dto) {
        return service.createMeetings(dto);
    }

    @Override
    public List<MeetingResponse> getMeetings(MeetingsRequest dto) {
        return service.getMeetings(dto);
    }

    @Override
    public StatusResponse deleteMeetings(MeetingsCancelRequest dto) {
        service.cancelMeetings(dto);
        return new StatusResponse("ok", null);
    }
}
