package com.toypro.test.toypro.config;

import org.springframework.context.annotation.Bean;

/**
 * version 0.0.1
 * 최초 생성 : 2024/01/04
 * 설명 : Spring Security 설정
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(
            (csrfConfig) -> csrfConfig.disable()
        ).headers(
            (headerConfig) ->  headerConfig.frameOptions(
                frameOptionsConfig -> frameOptionsConfig.disable())
        );

        return http.build();
	}

}
