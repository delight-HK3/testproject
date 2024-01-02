package com.toypro.test.toypro.service;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : 각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 클래스
 */

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.config.Constant.SocialLoginType;
import com.toypro.test.toypro.service.social.SocialOauth;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthService {
    
    private final List<SocialOauth> SocialOauthList;
    private final HttpServletResponse response;
    
    /**
     * 인증 호출 코드
     * 
     * @param socialLoginType
     * @throws IOException
     */
    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try{
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 소셜 로그인 API 서버로부터 받은 호출 코드
     * 
     * @param socialLoginType
     * @param code
     * @return
     * @throws IOException
     */
    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.requestAccessToken(code);
    }
    
    /**
     * SocialLoginType에 맞는 SocialOauth 객체를 반환
     * 
     * @param socialLoginType
     * @return
     */
    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType){
        return SocialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
}
