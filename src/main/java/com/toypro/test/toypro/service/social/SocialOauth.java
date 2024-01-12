package com.toypro.test.toypro.service.social;

import com.toypro.test.toypro.type.UserType;

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

    /**
     * API Server로부터 받은 code를 활용하여 사용자 인증 정보 요청
     * @param code API Server 에서 받아온 code
     * @return API 서버로 부터 응답받은 Json 형태의 결과를 string으로 반환
     */
    String requestAccessToken(String code);

    /**
     * requestAccessToken메서드에서 발급받은 access_token 활용하여 사용자 정보 요청
     * @param access_token requestAccessToken메서드에서 발급받은 access_token
     * @return API 서버로 부터 응답받은 Json 형태의 결과를 string으로 반환
     */
    String requestUserInfo(String access_token);

    default UserType type(){
        if(this instanceof GoogleOauth){ // 
            return UserType.GOOGLE;
        } 
        else {
            return null;
        }
    }
}
