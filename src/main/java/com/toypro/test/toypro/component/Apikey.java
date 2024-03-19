package com.toypro.test.toypro.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Apikey {
    
    // 네이버 클라이언트 키
    @Value("${naver.api.clientID}")
    private String naverClientId;

    // 네이버 시크릿 키
    @Value("${naver.api.secret}")
    private String naverSecretKey;

    // 구글 지도 키
    @Value("${google.map.Key}")
    private String googleKey;

    // 카카오 클라이언트 키
    @Value("${kakao.api.clientID}")
    private String kakaoKey;

    // 공공데이터 인증키
    @Value("${data.api.key}")
    private String datakey;

    // 구글 지도 출력 링크
    public String googleMap(){
        return "https://maps.googleapis.com/maps/api/js?key="+googleKey+"&callback=initMap&v=weekly";
    }

    // 네이버 지도 출력 링크
    public String naverMap(){
        return "https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId="+naverClientId;
    }

    // 카카오 지도 출력 링크
    public String kakaoMap(){
        return "//dapi.kakao.com/v2/maps/sdk.js?appkey="+kakaoKey+"&libraries=clusterer";
    }

    // 
    public String dataKey(){
        return this.datakey;
    }
}
