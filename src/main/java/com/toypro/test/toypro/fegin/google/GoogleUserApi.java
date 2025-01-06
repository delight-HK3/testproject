package com.toypro.test.toypro.fegin.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toypro.test.toypro.config.FeignConfiguration;

@FeignClient(value = "googleUser", url="https://www.googleapis.com", configuration = {FeignConfiguration.class})
public interface GoogleUserApi {
    @GetMapping("/userinfo/v2/me") // Resource 서버로부터 사용자정보 요청
    ResponseEntity<String> getUserInfo(@RequestParam("access_token") String accessToken);
}
