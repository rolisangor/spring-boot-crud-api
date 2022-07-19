package com.crud.springbootcrud.service;

import com.crud.springbootcrud.model.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);
    List<UserDto> getAllUsers(int page, int size);
    UserDto getUserById(Long id);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);

}
