package com.toypro.test.toypro.model;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/28
 * 설명 : 클라이언트로 보낼 jwtToken, accessToken등이 담긴 객체
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
public class GetSocialOAuthRes {
    private String jwtToken;
    private int user_num;
    private String accessToken;
    private String tokenType;
}
