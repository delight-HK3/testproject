package com.toypro.test.toypro.type;
/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : 소셜 로그인 타입을 구별하는 클래스
 */

import java.util.Arrays;

public enum UserType {
    NORMAL("normal"), 
    GOOGLE("google"), 
    KAKAO("kakao"), 
    NAVER("naver");

    private String name;

    UserType(String name){
        this.name = name;
    }
    
    public static UserType findSocialOauthType(String userType){
        return Arrays.stream(UserType.values())
                .filter(x -> x.name.equals(userType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }

    public static String getOauthType (String userType) {
        return findSocialOauthType(userType).getName();
    }

    public String getName() { return name; }
}

