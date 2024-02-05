package com.toypro.test.toypro.service.social;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

    // application.properties 파일에서 가져온다.
    @Value("${spring.OAuth2.Naver.url}")
    private String NAVER_SNS_LOGIN_URL;

    @Value("${spring.OAuth2.Naver.client-id}")
    private String NAVER_SNS_CLIENT_ID;

    @Value("${spring.OAuth2.Naver.callback-url}")
    private String NAVER_SNS_CALLBACK_URL;

    @Value("${spring.OAuth2.Naver.client-secret}")
    private String NAVER_SNS_CLIENT_SECRET;


    @Override
    public UserType getServiceName() {
        return UserType.NAVER;
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
