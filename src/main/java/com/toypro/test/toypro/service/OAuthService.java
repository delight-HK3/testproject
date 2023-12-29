package com.toypro.test.toypro.service;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : 각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 클래스
 */

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.toypro.test.toypro.account.social.GoogleOauth;
import com.toypro.test.toypro.config.Constant;
import com.toypro.test.toypro.config.Constant.SocialLoginType;
import com.toypro.test.toypro.model.GetSocialOAuthRes;
import com.toypro.test.toypro.model.GoogleAuthToken;
import com.toypro.test.toypro.model.GoogleUser;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthService {
    
    private final GoogleOauth googleOauth;
    private final HttpServletResponse httpServletResponse;
    
    /**
     * 인증 호출 코드
     * 
     * @param socialLoginType
     * @throws IOException
     */
    public void request(SocialLoginType socialLoginType) throws IOException {
        String redirectURL;
        switch(socialLoginType){
            case GOOGLE:{
                redirectURL = googleOauth.getOauthRedirectURL();
            } break;
            default:{
                throw new IllegalStateException("알 수 없는 로그인 방식 입니다.");
            }
        }
        try{
            httpServletResponse.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public String requestAccessToken(SocialLoginType socialLoginType, String code) throws IOException{
        
        switch (socialLoginType) {
            case GOOGLE:{
                return googleOauth.requestAccessToken(code);
            }
            default: {
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }
                
        }
    }
    
}
