package com.crud.springbootcrud.controller;

import com.crud.springbootcrud.model.dto.TokenResponseDto;
import com.crud.springbootcrud.model.dto.UserLoginDto;
import com.crud.springbootcrud.security.JwtUtils;
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
public class AuthenticationController {

    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(jwtUtils.getJwtTokenResponse(userLoginDto.getUsername(), userLoginDto.getPassword()));
    }
}
