package com.crud.springbootcrud.config;

import com.crud.springbootcrud.service.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@AllArgsConstructor
public class DefaultSecurityConfig {

    private final CustomAuthenticationProvider authenticationProvider;
    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        return http.oauth2ResourceServer(
                oauth2 -> oauth2.jwt().jwkSetUri("http://localhost:9000/oauth2/jwks")
        ).authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

}
