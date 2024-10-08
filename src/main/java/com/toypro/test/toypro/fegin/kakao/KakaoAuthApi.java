package com.toypro.test.toypro.fegin.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toypro.test.toypro.config.FeignConfiguration;

@FeignClient(value = "kakaoAuth", url="https://kauth.kakao.com", configuration = {FeignConfiguration.class})
public interface KakaoAuthApi {
    @GetMapping("/oauth/token")
    ResponseEntity<String> getAccessToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("grant_type") String grantType,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String authorizationCode
    );
} 
