package com.crud.springbootcrud.service;

import com.crud.springbootcrud.model.User;
import com.crud.springbootcrud.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User save(User user) { // TODO: return UserDto
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() { //TODO: return List UserDto
        return userRepository.findAll();
    }
}
