package com.toypro.test.toypro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.repository.account.AccountRepository;
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
        
        SocialLoginService loginService = this.getLoginService(userType);
        SocialAuthResponse socialAuthResponse = loginService.getAccessToken(code);

        SocialUserResponse socialUserResponse = loginService.getUserInfo(socialAuthResponse.getAccess_token());
        log.info("socialUserResponse {} ", socialUserResponse.toString());
        
        return socialUserResponse;
    }

    private SocialLoginService getLoginService(UserType userType){
        for (SocialLoginService loginService: loginServices) {
            if (userType.equals(loginService.getServiceName())) {
                log.info("login service name: {}", loginService.getServiceName());
                return loginService;
            }
        }
        return new NormalLoginServiceImpl();
    }

    public void request(UserType userType) {
        SocialLoginService socialLoginService = this.findSocialOauthByType(userType);
        String redirectURL = socialLoginService.getOauthRedirectURL();
        try{
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
