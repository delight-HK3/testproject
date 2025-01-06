package com.toypro.test.toypro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.service.social.NormalLoginServiceImpl;
import com.toypro.test.toypro.service.social.SocialLoginService;
import com.toypro.test.toypro.type.UserType;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final List<SocialLoginService> loginServices;
    private final HttpServletResponse response;
    
    public SocialUserResponse doSocialLogin(UserType userType, String code) {
        SocialUserResponse socialUserResponse; // sns로그인 결과값
        SocialLoginService loginService;

        // 일반사용자와 SNS로그인 사용자 구분
        if(userType.NORMAL.equals(userType)){
            loginService = this.getLoginService(userType);
            socialUserResponse = loginService.getUserInfo(code);
        } else {
            loginService = this.getLoginService(userType);
            SocialAuthResponse socialAuthResponse = loginService.getAccessToken(code);

            socialUserResponse = loginService.getUserInfo(socialAuthResponse.getAccess_token());
            log.info("socialUserResponse {} ", socialUserResponse.toString());
        }
        
        return socialUserResponse;
    }

    private SocialLoginService getLoginService(UserType userType){
        for (SocialLoginService loginService: loginServices) {
            if (userType.equals(loginService.getServiceName())) {
                log.info("login service name: {}", loginService.getServiceName());
                return loginService;
            }
        }
        return new NormalLoginServiceImpl(null);
    }

    // sns로그인 시도시 Authorization서버에서 제공하는 페이지 호출
    public void LoginFormrequest(UserType userType) {
        
        SocialLoginService socialLoginService = this.findSocialOauthByType(userType);
        
        String redirectURL = socialLoginService.getOauthRedirectURL();
        
        try{    
            // 각 sns로그인 관련 SNS service에서 생성한 호출 URL 호출
            response.sendRedirect(redirectURL); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }


    private SocialLoginService findSocialOauthByType(UserType userType){
        return loginServices.stream()
                .filter(x -> x.type() == userType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
}
