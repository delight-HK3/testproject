package com.toypro.test.toypro.fegin.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.toypro.test.toypro.config.FeignConfiguration;
import com.toypro.test.toypro.dto.google.GoogleRequestAccessTokenDto;

@FeignClient(value = "googleAuth", url="https://oauth2.googleapis.com", configuration = {FeignConfiguration.class})
public interface GoogleAuthApi {
    @PostMapping("/token") // authrization 서버로 부터 토큰 요청
    ResponseEntity<String> getAccessToken(@RequestBody GoogleRequestAccessTokenDto requestDto);
}
