package com.toypro.test.toypro.account.social;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : 소셜 로그인 클래스가 상속받아 사용할 수 있도록 interface 생성
 */
public interface SocialOauth {
    /**
     * 각 소셜 로그인 페이지로 redirect할 URL build
     * 사용자로부터 로그인 요청을 받아 소셜 로그인 서버 인증용 코드 요청
     */
    String getOauthRedirectURL();
}
