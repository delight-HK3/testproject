package com.toypro.test.toypro.service.social;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.type.UserType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Qualifier("LoginService")
public class NormalLoginServiceImpl implements SocialLoginService {
    
    @Override
    public UserType getServiceName() {
        return UserType.NORMAL;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccessToken'");
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        System.out.println("노멀로그인 서비스 impl");
        throw new UnsupportedOperationException("Unimplemented method 'getUserInfo'");
    }

    @Override
    public String getOauthRedirectURL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOauthRedirectURL'");
    }


}
