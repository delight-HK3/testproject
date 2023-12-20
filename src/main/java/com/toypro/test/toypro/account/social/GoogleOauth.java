package com.toypro.test.toypro.account.social;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth{
    // 소셜 로그인 타입별 클래스

    private final ObjectMapper objectMapper;

    @Override
    public String getOauthRedirectURL() {
        return "";
    }
    
}
