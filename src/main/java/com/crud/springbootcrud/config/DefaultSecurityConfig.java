package com.crud.springbootcrud.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@AllArgsConstructor
public class DefaultSecurityConfig {

    private final CorsCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        return http.oauth2ResourceServer(
                oauth2 -> oauth2.jwt()
                        .jwkSetUri("http://localhost:9000/oauth2/jwks")
//                        .jwtAuthenticationConverter(authenticationConverter)
        ).authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new TestFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .and()
                .build();

    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers(HttpMethod.POST, "/api/user/sign-up");
    }
}
