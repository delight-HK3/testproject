package com.toypro.test.toypro.dto.social;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.toypro.test.toypro.entity.account.AccountEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SocialUserResponse {
    private String snsType;         // 로그인 한 sns 타입
    private String accessToken;     // accessToken 값

    // 유저 정보
    private int seq;                // 회원번호
    private String id;              // 아이디
    private String pwd;             // 비밀번호      
    private String email;           // 이메일
    private String nickname;        // 닉네임
    private String name;            // 이름
    private String age;             // 나이
    private String gender;          // 성별
    private String birthday;        // 생일날짜
    private String birthyear;       // 생일년도
    private String mobile;          // 모바일
    private String mobile_e164;

    // entity -> DTO (유저정보 가져오기)
    public static SocialUserResponse toLoginDTO(AccountEntity entity){

        return SocialUserResponse.builder()
                            .id(entity.getUserId())
                            .pwd(entity.getUserPwd())
                            .name(entity.getUserName())
                            .nickname(entity.getNickname())
                            .email(entity.getUserEmail())
                            .mobile(entity.getPhoneNo())
                            .snsType(entity.getUsertype())
                            .build();
    }
}
