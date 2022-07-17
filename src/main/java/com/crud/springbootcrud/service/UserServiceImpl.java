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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(Long id) {
        return userMapper.toUserDto(userRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found")));
    }

    private boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
