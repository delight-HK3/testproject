package com.toypro.test.toypro.service.account;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.account.AccountRequestDto;
import com.toypro.test.toypro.dto.social.SocialUserResponse;

@Service
public interface AccountService {

    String mailConfirm(String email); // 회원가입 - 이메일 인증 번호 보내기
    
    String signin(AccountRequestDto signinRequestDto, String checkKey); // 회원가입

    void snsSignin(SocialUserResponse socialUserResponse); // 회원가입 - sns 계정 로그인 유저 가입

    String searchUser(String userid); // 회원가입 - 중복 아이디 체크

    String searchNickName(String nickName); // 회원가입 - 중복 닉네임 체크

    int searchUserNo(String userId); // 회원가입 - 유저번호 찾기
}