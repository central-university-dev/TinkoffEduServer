package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.UserAuthDetails;
import com.tinkoffedu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@Mapper(config = MapStructConfig.class)
public interface UserAuthDetailsMapper {

    @Mapping(target = "authorities", source = "authorities")
    UserAuthDetails map(User user, List<GrantedAuthority> authorities);
}
