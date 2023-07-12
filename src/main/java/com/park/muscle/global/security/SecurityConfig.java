package com.park.muscle.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final ObjectMapper mapper;

    public SecurityConfig(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint(mapper))
                    .accessDeniedHandler(new RestAccessDeniedHandler(mapper))
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                // TODO: 인증되는 사용자의 정보를 검증할 필터를 추가해야 할 것 같음
//                .and()
//                    .apply(new JwtSecurityConfig(jwtTokenProvider, mapper))
                .and()
                    .formLogin().disable();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers("/favicon.ico");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}