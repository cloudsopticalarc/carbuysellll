package com.spring.jwt.config;

import com.spring.jwt.jwt.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfigConfig {

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}