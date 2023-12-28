package com.toypro.test.toypro.model;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/28
 * 설명 : 구글(서드파티)로 액세스 토큰을 보내 받아올 구글에 등록된 사용자 정보
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
public class GoogleUser {
    public String id;
    public String email;
    public Boolean verifiedEmail;
    public String name;
    public String givenName;
    public String familyName;
    public String picture;
    public String locale;
}
