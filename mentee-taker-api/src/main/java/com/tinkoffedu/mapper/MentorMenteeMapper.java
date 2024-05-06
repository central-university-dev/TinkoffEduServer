package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.mentee.MentorMenteeRequest;
import com.tinkoffedu.entity.MentorMentee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapStructConfig.class)
public interface MentorMenteeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetings", ignore = true)
    MentorMentee map(MentorMenteeRequest dto);
}
