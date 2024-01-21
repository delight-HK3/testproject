package com.toypro.test.toypro.controller;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : SNS로그인 기능 컨트롤러 클래스
 */

import java.io.IOException;
import java.net.URI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.service.UserService;
import com.toypro.test.toypro.type.UserType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app/accounts")
public class AccountController {
    
    private final UserService userService;
    

    @GetMapping(value = "auth/{socialLoginType}")
    public void socialLoginType(@PathVariable(name="socialLoginType") UserType userType) throws IOException {

        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", userType);
        userService.request(userType);
    }

    /**
     * 유저 소셜 로그인으로 리다이렉트 해주는 url
     * 
     * @param SocialLoginPath
     * @throws IOException
     * @return void
     */
    @GetMapping(value = "/auth/{socialLoginType}/callback")
    public String socialLogin(
            @PathVariable(name = "socialLoginType") UserType userType,
            @RequestParam(name = "code") String code) {

       //return ResponseEntity.created(URI.create("/auth"))
         //       .body(userService.doSocialLogin(userType, code));
        SocialUserResponse socialUserResponse = userService.doSocialLogin(userType, code);

        //System.out.println(socialUserResponse);

        return "";            
    }
    
}
