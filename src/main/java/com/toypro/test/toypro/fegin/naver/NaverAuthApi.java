package com.toypro.test.toypro.fegin.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toypro.test.toypro.config.FeignConfiguration;

@FeignClient(value = "naverAuth", url="https://nid.naver.com", configuration = {FeignConfiguration.class})
public interface NaverAuthApi {
    @GetMapping("/oauth2.0/token")
    ResponseEntity<String> getAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("code") String code,
            @RequestParam("state") String state
    );
}
