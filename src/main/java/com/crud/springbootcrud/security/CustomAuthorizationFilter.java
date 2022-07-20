package com.crud.springbootcrud.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.crud.springbootcrud.controller.advice.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private static final String TOKEN_TYPE = "Bearer ";
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("Request path is {}", request.getServletPath());

        if (request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("api/auth/registration")) {
            filterChain.doFilter(request, response);
        } else {
            if (hasAuthorizationBearer(request)) {
                try {
                    jwtUtils.validateJwtToken(request.getHeader(AUTHORIZATION));
                    filterChain.doFilter(request, response);
                } catch (JWTVerificationException e) {
                    log.error("TOKEN_VALIDATION_ERROR: {}", e.getMessage());

                    response.setStatus(FORBIDDEN.value());
                    response.setContentType(APPLICATION_JSON_VALUE);

                    ResponseError errorBody = ResponseError.builder()
                            .status(FORBIDDEN.value())
                            .timestamp(LocalDateTime.now())
                            .errors(List.of(e.getMessage()))
                            .build();

                    sendResponseError(response, errorBody);
                }
            } else {
                response.setStatus(FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);

                ResponseError errorBody = ResponseError.builder()
                        .status(FORBIDDEN.value())
                        .timestamp(LocalDateTime.now())
                        .errors(List.of("Token must be present"))
                        .build();

                sendResponseError(response, errorBody);
            }
        }
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);
        return !ObjectUtils.isEmpty(header) && header.startsWith(TOKEN_TYPE);
    }

    private void sendResponseError(HttpServletResponse response, ResponseError body) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
