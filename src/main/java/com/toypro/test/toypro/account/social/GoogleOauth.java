package com.toypro.test.toypro.account.social;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth{
    // 소셜 로그인 타입별 클래스

    @Override
    public String getOauthRedirectURL() {
        return "";
    }
    
}
