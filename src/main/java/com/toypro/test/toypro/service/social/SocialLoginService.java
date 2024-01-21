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
        if(this instanceof GoogleLoginServiceImpl){ // 
            return UserType.GOOGLE;
        } 
        else {
            return null;
        }
    }
}
