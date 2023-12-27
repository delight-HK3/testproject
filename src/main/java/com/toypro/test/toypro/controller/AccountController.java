package com.toypro.test.toypro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toypro.test.toypro.config.Constant.SocialLoginType;
import com.toypro.test.toypro.service.OAuthService;

@RestController
@RequestMapping("/app/accounts")
public class AccountController {
    
    @Autowired
    OAuthService oAuthService;
    
    
    @GetMapping("auth/{socialLoginType}")
    public void socialLoginRedirect(@PathVariable(name="socialLoginType") String SocialLoginPath) throws IOException {
        System.out.println("google");
        
        SocialLoginType socialLoginType = SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
        oAuthService.request(socialLoginType);
    }
}
