package com.toypro.test.toypro.service.login;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.service.social.SocialLoginService;
import com.toypro.test.toypro.type.UserType;

@Service
@Component
@Qualifier("defaultLoginService")
public class LoginServiceImpl implements SocialLoginService {
    @Override
    public UserType getServiceName() {
        return UserType.NORMAL;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        return null;
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        return null;
    }
    
    @Override
    public String getOauthRedirectURL() {
        throw new UnsupportedOperationException("Unimplemented method 'getOauthRedirectURL'");
    }
}
