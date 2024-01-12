package com.toypro.test.toypro.service;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : 각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 클래스
 */

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.service.social.SocialOauth;
import com.toypro.test.toypro.type.UserType;

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
     * @param userType
     * @throws IOException
     */
    public void request(UserType userType) {
        SocialOauth socialOauth = this.findSocialOauthByType(userType);
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
     * @param userType
     * @param code
     * @return
     */
    public String requestAccessToken(UserType userType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(userType);
        return socialOauth.requestAccessToken(code);
    }
    
    /**
     * 소셜 로그인 토큰으로 서버로부터 유저정보 얻어내는 코드
     * 
     * @param userType
     * @param access_token
     * @return
     */
    public String requestUserInfo(UserType userType, String access_token) {
        SocialOauth socialOauth = this.findSocialOauthByType(userType);
        return socialOauth.requestUserInfo(access_token);
    }

    /**
     * SocialLoginType에 맞는 SocialOauth 객체를 반환
     * 
     * @param userType
     * @return
     */
    private SocialOauth findSocialOauthByType(UserType userType){
        return SocialOauthList.stream()
                .filter(x -> x.type() == userType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }

}
