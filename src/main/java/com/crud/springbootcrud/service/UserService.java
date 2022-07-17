package com.crud.springbootcrud.service;

import com.crud.springbootcrud.model.User;
import com.crud.springbootcrud.model.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
}
