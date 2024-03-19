package com.toypro.test.toypro.service.social;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.toypro.test.toypro.dto.kakao.KaKaoLoginResponse;
import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.fegin.kakao.KakaoAuthApi;
import com.toypro.test.toypro.fegin.kakao.KakaoUserApi;
import com.toypro.test.toypro.type.UserType;
import com.toypro.test.toypro.utils.GsonLocalDateTimeAdapter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Qualifier("kakaoLogin")
public class KakaoLoginServiceImpl implements SocialLoginService {
    
    private final KakaoAuthApi kakaoAuthApi;
    private final KakaoUserApi kakaoUserApi;

    @Value("${spring.Oauth2.Kakao.client-id}")
    private String KAKAO_SNS_CLIENT_ID;

    @Value("${spring.Oauth2.Kakao.client-secret}")
    private String KAKAO_SNS_CLIENT_SECRET;

    @Value("${spring.Oauth2.Kakao.url}")
    private String KAKAO_SNS_LOGIN_URL;

    @Value("${spring.OAuth2.Kakao.scope}")
    private String KAKAO_DATA_ACCESS_SCOPE;

    @Value("${spring.Oauth2.Kakao.callback-url}")
    private String KAKAO_SNS_CALLBACK_URL;

    @Override
    public UserType getServiceName() {
        return UserType.KAKAO;
    }
    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        
        ResponseEntity<?> response = kakaoAuthApi.getAccessToken(
            KAKAO_SNS_CLIENT_ID
            , KAKAO_SNS_CLIENT_SECRET
            , "authorization_code"
            , KAKAO_SNS_CALLBACK_URL
            , authorizationCode
        );

         return new Gson()
            .fromJson(
                String.valueOf(response.getBody())
                , SocialAuthResponse.class
            );
    }
    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        Map<String ,String> headerMap = new HashMap<>();
        headerMap.put("authorization", "Bearer " + accessToken);

        ResponseEntity<?> response = kakaoUserApi.getUserInfo(headerMap);

        String jsonString = String.valueOf(response.getBody());
    
        Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
        .create();

        KaKaoLoginResponse kaKaoLoginResponse = gson.fromJson(jsonString, KaKaoLoginResponse.class);
        KaKaoLoginResponse.KakaoLoginData kakaoLoginData = Optional.ofNullable(kaKaoLoginResponse.getKakao_account())
            .orElse(KaKaoLoginResponse.KakaoLoginData.builder().build());

        String name = Optional.ofNullable(kakaoLoginData.getProfile())
                .orElse(KaKaoLoginResponse.KakaoLoginData.KakaoProfile.builder().build())
                .getNickname();
        
        return SocialUserResponse.builder()
                .id(kaKaoLoginResponse.getId())
                .gender(kakaoLoginData.getGender())
                .name(name)
                .email(kakaoLoginData.getEmail())
                .build();
    }   

    @Override
    public String getOauthRedirectURL() {
        
        Map<String,Object> params = new HashMap<>();

        params.put("redirect_uri",KAKAO_SNS_CALLBACK_URL);
        params.put("client_id",KAKAO_SNS_CLIENT_ID);
        params.put("response_type","code");
        params.put("scope", KAKAO_DATA_ACCESS_SCOPE);

        //parameter를 형식에 맞춰 구성해주는 함수
        String parameterString = params.entrySet().stream()
                .map(x->x.getKey()+"="+x.getValue())
                .collect(Collectors.joining("&"));

        String redirectURL = KAKAO_SNS_LOGIN_URL+"?"+parameterString;
        System.out.println("redirectURL = " + redirectURL);

        return redirectURL;
    }

}
