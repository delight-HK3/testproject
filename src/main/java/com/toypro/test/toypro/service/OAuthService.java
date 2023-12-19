package com.toypro.test.toypro.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.account.social.GoogleOauth;
import com.toypro.test.toypro.config.Constant;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthService {
    
    private final GoogleOauth googleOauth;
    private final HttpServletResponse httpServletResponse;
    
    public void request(Constant.SocialLoginType socialLoginType) throws IOException {
        String redirectURL;
        switch(socialLoginType){
            case GOOGLE:{
                //각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 프로세스이다.
                redirectURL = googleOauth.getOauthRedirectURL();
            } break;
            default:{
                throw new IllegalStateException("알 수 없는 로그인 방식 입니다.");
            }
        }
        
        httpServletResponse.sendRedirect(redirectURL);
    }
}
