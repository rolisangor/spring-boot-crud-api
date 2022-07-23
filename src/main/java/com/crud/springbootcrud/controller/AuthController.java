package com.crud.springbootcrud.controller;

import com.crud.springbootcrud.model.dto.UserDto;
import com.crud.springbootcrud.model.dto.UserRegistrationDto;
import com.crud.springbootcrud.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody UserRegistrationDto userRegistrationDto) {
        return ResponseEntity.ok(userService.save(userRegistrationDto));
    }
}
