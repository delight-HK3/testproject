package com.toypro.test.toypro.service.account;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.signin.SigninRequestDto;
import com.toypro.test.toypro.dto.social.SocialUserResponse;

@Service
public interface AccountService {

    String mailConfirm(String email); // 인증 번호 보내기
    
    String signin(SigninRequestDto signinRequestDto, String checkKey); // 회원가입

    String snsSignin(SocialUserResponse socialUserResponse); // sns 계정 로그인 유저 가입
}