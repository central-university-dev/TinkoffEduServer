package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.meeting.MeetingResponse;
import com.tinkoffedu.entity.Meeting;
import org.mapstruct.Mapper;


@Mapper(config = MapStructConfig.class)
public interface MeetingMapper {

    MeetingResponse map(Meeting meeting, String mentorName, String menteeName);

}
