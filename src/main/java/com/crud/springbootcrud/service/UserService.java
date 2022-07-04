package com.crud.springbootcrud.service;

import com.crud.springbootcrud.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> getAllUsers();
}
