package com.crud.springbootcrud.service;

import com.crud.springbootcrud.exception.BadRequestException;
import com.crud.springbootcrud.exception.InternalServerError;
import com.crud.springbootcrud.exception.UserNotFoundException;
import com.crud.springbootcrud.model.User;
import com.crud.springbootcrud.model.dto.UserDto;
import com.crud.springbootcrud.repository.UserRepository;
import com.crud.springbootcrud.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto save(UserDto userDto) {
        if (existByEmail(userDto.getEmail())) {
            throw new BadRequestException("User with email: " + userDto.getEmail() + " already exist");
        }
        User user;
        try {
            user = userRepository.save(userMapper.toUser(userDto));
        }catch (Exception e) {
            throw new InternalServerError("Internal server error please try again");
        }
        return userMapper.toUserDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName"));
        return userMapper.toUserDtoList(userRepository.findAll(pageable).toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(Long id) {
        return userMapper.toUserDto(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with id: " + id + " not found")));
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() ->
                new UserNotFoundException("User with id: " + userDto.getId() + " not found"));
        userMapper.updateFromUserDto(userDto, user);
        return userMapper.toUserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e) {
            throw new InternalServerError("Internal server error please try again");
        }
    }

    private boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
