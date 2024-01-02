package com.toypro.test.toypro.controller;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : SNS로그인 기능 컨트롤러 클래스
 */

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toypro.test.toypro.config.Constant.SocialLoginType;
import com.toypro.test.toypro.service.OAuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/app/accounts")
@Slf4j
public class AccountController {
    
    @Autowired
    OAuthService oAuthService;
    
    /**
     * 유저 소셜 로그인으로 리다이렉트 해주는 url
     * 
     * @param SocialLoginPath
     * @throws IOException
     * @return void
     */
    @GetMapping(value = "auth/{socialLoginType}")
    public void socialLoginRedirect(@PathVariable(name="socialLoginType") SocialLoginType socialLoginType) throws IOException {

        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oAuthService.request(socialLoginType);
    }

    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     * 
     * @param socialLoginPath (GOOGLE, FACEBOOK, NAVER, KAKAO)
     * @param code (API Server 로부터 넘어오는 code)
     * @return (SNS Login 요청 결과로 받은 Json 형태의 java 객체 (access_token, jwt_token, user_num 등))
     * @throws IOException
     * @throws BaseException
     */

     
    @GetMapping(value = "/auth/{socialLoginType}/callback")
    public String callback (
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestParam(name = "code") String code) {

        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        return "";
    }
    
}
