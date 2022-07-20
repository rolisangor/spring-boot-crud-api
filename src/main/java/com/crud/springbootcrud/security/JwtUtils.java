package com.crud.springbootcrud.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.crud.springbootcrud.exception.AuthenticationError;
import com.crud.springbootcrud.exception.TokenValidationException;
import com.crud.springbootcrud.model.dto.TokenResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtils {

    @Value("${crud.app.jwtSecret}")
    private String JTW_TOKEN_KEY;

    @Value("${crud.app.jwtExpirationMs}")
    private Long JWT_TOKEN_EXPIRATION_MS;

    private static final String TOKEN_TYPE = "Bearer ";
    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtUtils(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }

    public Authentication authenticateUser(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken;
        try{
            authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e) {
            log.error("AUTHENTICATION_ERROR : {}", e.getMessage());
            throw new AuthenticationError("Authentication user error: " + e.getMessage());
        }
    }

    public TokenResponseDto getJwtTokenResponse(String username, String password) throws AuthenticationError {
        Authentication authentication = authenticateUser(username, password);
        return TokenResponseDto.builder()
                .accessToken(generateAccessToken(authentication))
                .build();
    }

    public String generateAccessToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(JTW_TOKEN_KEY.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles",
                        user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_MS))
                .sign(algorithm);
    }

    public void validateJwtToken(String authorizationHeader) throws JWTVerificationException{
            String token = authorizationHeader.substring(TOKEN_TYPE.length());
            Algorithm algorithm = Algorithm.HMAC256(JTW_TOKEN_KEY.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            setAuthenticationContext(decodedJWT);

    }

    private void setAuthenticationContext(DecodedJWT decodedJWT) {
        String username = decodedJWT.getSubject();

        Collection<SimpleGrantedAuthority> authorities = decodedJWT.getClaim("roles")
                .asList(String.class).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
