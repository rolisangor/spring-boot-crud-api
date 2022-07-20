package com.crud.springbootcrud.config;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
@Order(1)
public class TestSecurityConfiguration {




//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable().cors().disable()
//                .authorizeHttpRequests()
//                .anyRequest().permitAll()
//                .and()
//                .build();
//    }
}
