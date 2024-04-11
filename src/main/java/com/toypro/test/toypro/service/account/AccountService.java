package com.toypro.test.toypro.service.account;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.signin.SigninRequestDto;

@Service
public interface AccountService {

    String mailConfirm(String email); // 인증 번호 보내기
    
    String signin(SigninRequestDto signinRequestDto, String checkKey); // 회원가입
}