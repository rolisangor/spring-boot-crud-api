package com.crud.springbootcrud.service.mapper;

import com.crud.springbootcrud.model.User;
import com.crud.springbootcrud.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
