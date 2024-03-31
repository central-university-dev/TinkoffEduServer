package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.dto.user.UserResponse;
import com.tinkoffedu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapStructConfig.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User map(UserRequest dto);

    UserResponse map(User user);
}
