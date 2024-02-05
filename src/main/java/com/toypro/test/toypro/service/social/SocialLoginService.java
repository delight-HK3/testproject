package com.toypro.test.toypro.service.social;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.type.UserType;

@Service
public interface SocialLoginService {
    UserType getServiceName();
    SocialAuthResponse getAccessToken(String authorizationCode);
    SocialUserResponse getUserInfo(String accessToken);
    String getOauthRedirectURL();

    default UserType type(){
        if(this instanceof GoogleLoginServiceImpl){ // 구글 로그인
            return UserType.GOOGLE;
        } 
        else if(this instanceof NaverLoginServiceImpl){ // 네이버 로그인
            return UserType.NAVER;
        }
        else {
            return null;
        }
    }
}
