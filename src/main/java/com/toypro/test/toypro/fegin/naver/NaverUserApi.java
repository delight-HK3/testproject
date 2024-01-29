package com.toypro.test.toypro.fegin.naver;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.toypro.test.toypro.config.FeignConfiguration;

@FeignClient(value = "naverUser", url="https://openapi.naver.com", configuration = {FeignConfiguration.class})
public interface NaverUserApi {
    @GetMapping("/v1/nid/me")
    ResponseEntity<String> getUserInfo(@RequestHeader Map<String, String> header);
}
