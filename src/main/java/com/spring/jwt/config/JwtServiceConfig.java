package com.spring.jwt.config;

import com.spring.jwt.jwt.JwtConfig;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.jwt.impl.JwtServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class JwtServiceConfig {

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

    @Bean
    public JwtService jwtService(JwtConfig jwtConfig) {
        return new JwtServiceImpl(jwtConfig);
    }
}