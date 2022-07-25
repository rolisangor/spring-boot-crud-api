//package com.crud.springbootcrud.service;
//
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        UserDetails userDetails = userService.loadUserByUsername(username);
//        return getAuthentication(userDetails, password);
//    }
//
//    private Authentication getAuthentication(UserDetails userDetails, String password) {
//        if (passwordEncoder.matches(password, userDetails.getPassword())) {
//            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
//        }else {
//            throw new BadCredentialsException("Bad Credentials");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
