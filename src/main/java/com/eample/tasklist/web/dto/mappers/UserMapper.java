package com.eample.tasklist.web.dto.mappers;

import com.eample.tasklist.domain.user.User;
import com.eample.tasklist.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
