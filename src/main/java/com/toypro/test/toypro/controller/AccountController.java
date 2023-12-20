package com.toypro.test.toypro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.toypro.test.toypro.config.Constant.SocialLoginType;
import com.toypro.test.toypro.service.OAuthService;

@Controller
public class AccountController {
    
    @Autowired
    OAuthService oAuthService;
    
    @GetMapping("/auth/{socialLoginType}")
    public void socialLoginRedirect(@PathVariable(name="socialLoginType") String SocialLoginPath) throws IOException {
        SocialLoginType socialLoginType = SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
        oAuthService.request(socialLoginType);
    }
}
