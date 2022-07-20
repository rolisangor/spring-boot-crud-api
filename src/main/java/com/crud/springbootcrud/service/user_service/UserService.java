package com.crud.springbootcrud.service.user_service;

import com.crud.springbootcrud.model.user.User;
import com.crud.springbootcrud.model.dto.UserDto;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto) throws RoleNotFoundException;
    List<UserDto> getAllUsers(int page, int size);
    UserDto getUserById(Long id);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
    User getUserByEmail(String email);

}
