package com.toypro.test.toypro.service.social;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.fegin.naver.NaverAuthApi;
import com.toypro.test.toypro.fegin.naver.NaverUserApi;
import com.toypro.test.toypro.type.UserType;

import lombok.RequiredArgsConstructor;

@Component 
@Service
@RequiredArgsConstructor // 자동 의존성 주입
@Qualifier("naverLogin")
public class NaverLoginServiceImpl implements SocialLoginService{

    private final NaverAuthApi naverAuthApi;
    private final NaverUserApi naverUserApi;

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
