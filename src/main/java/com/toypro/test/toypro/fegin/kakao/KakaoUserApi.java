package com.toypro.test.toypro.fegin.kakao;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.toypro.test.toypro.config.FeignConfiguration;

@FeignClient(value = "kakaoUser", url="https://kapi.kakao.com", configuration = {FeignConfiguration.class})
public interface KakaoUserApi {
    @GetMapping("/v2/user/me") // Resource 서버로부터 accessToken에 맞는 유저정보 호출
    ResponseEntity<String> getUserInfo(@RequestHeader Map<String, String> header);
}
