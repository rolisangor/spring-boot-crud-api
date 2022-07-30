package com.crud.springbootcrud.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            log.info("SERVLET_PATH: {}", request.getServletPath());
            log.info("TOKEN: {}", request.getHeader("authorization"));

        }catch (Exception e) {
            log.error("REQUEST EXCEPTION: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
