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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getServiceName'");
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccessToken'");
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserInfo'");
    }

    @Override
    public String getOauthRedirectURL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOauthRedirectURL'");
    }


}
