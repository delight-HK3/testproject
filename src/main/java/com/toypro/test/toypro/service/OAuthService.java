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
    public void request(Constant.SocialLoginType socialLoginType) throws IOException {
        String redirectURL;
        switch(socialLoginType){
            case GOOGLE:{
                redirectURL = googleOauth.getOauthRedirectURL();
            } break;
            default:{
                throw new IllegalStateException("알 수 없는 로그인 방식 입니다.");
            }
        }
        
        httpServletResponse.sendRedirect(redirectURL);
    }
    /* 
    public GetSocialOAuthRes oAuthLogin(Constant.SocialLoginType socialLoginType, String code) throws IOException{
        
        switch (socialLoginType) {
            case GOOGLE:{
                // 구글로 일회성 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
                ResponseEntity<String> accessTokenResponse = googleOauth.requestAccessToken(code);
                // 응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
                GoogleAuthToken oAuthToken=googleOauth.getAccessToken(accessTokenResponse);

                // 액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
                ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo(oAuthToken);
                // 다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
                GoogleUser googleUser= googleOauth.getUserInfo(userInfoResponse);
                break;
            }
            default: {
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }
                
        }
    }
    */
}
