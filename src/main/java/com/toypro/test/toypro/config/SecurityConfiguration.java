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
        ).sessionManagement( // 세션 설정
            (sessisonConfig) -> sessisonConfig.invalidSessionUrl("/") // 세션이 없는 경우 기본페이지               
                                              .maximumSessions(1) // 최대 접속자 수
                                              .maxSessionsPreventsLogin(true) // 최대 허용세션인 경우 사용자 인증실패 처리
                                              .expiredUrl("/") // 세션이 만료된 경우 이동 할 페이지
            
        );

        return http.build();
	}

}
