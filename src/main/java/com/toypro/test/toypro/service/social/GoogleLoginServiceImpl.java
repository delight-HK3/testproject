package com.toypro.test.toypro.service.social;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : url을 구성해 service로 넘겨주면 service->controller를 
 * 거쳐 해당 url을 통해서 소셜 로그인 페이지로 리다이렉트 시켜주는 클래스
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.toypro.test.toypro.dto.google.GoogleLoginResponse;
import com.toypro.test.toypro.dto.google.GoogleRequestAccessTokenDto;
import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.fegin.google.GoogleAuthApi;
import com.toypro.test.toypro.fegin.google.GoogleUserApi;
import com.toypro.test.toypro.type.UserType;
import com.toypro.test.toypro.utils.GsonLocalDateTimeAdapter;

@Slf4j
@Component
@Service
@RequiredArgsConstructor
@Qualifier("googleLogin")
public class GoogleLoginServiceImpl implements SocialLoginService{

    private final GoogleAuthApi googleAuthApi;
    private final GoogleUserApi googleUserApi;

    // application.properties 파일에서 가져온다.
    @Value("${spring.OAuth2.google.url}")
    private String GOOGLE_SNS_LOGIN_URL;

    @Value("${spring.OAuth2.google.client-id}")
    private String GOOGLE_SNS_CLIENT_ID;

    @Value("${spring.OAuth2.google.callback-url}")
    private String GOOGLE_SNS_CALLBACK_URL;

    @Value("${spring.OAuth2.google.client-secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;

    @Value("${spring.OAuth2.google.scope}")
    private String GOOGLE_DATA_ACCESS_SCOPE;

    @Value("${spring.OAuth2.google.token.url}")
    private String GOOGLE_TOKEN_REQUEST_URL;

    @Value("${spring.OAuth2.google.token.user}")
    private String GOOGLE_ACCESS_TOKEN_URL;

    @Override
    public UserType getServiceName() {
        return UserType.GOOGLE;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        ResponseEntity<?> response = googleAuthApi.getAccessToken(
        GoogleRequestAccessTokenDto.builder()
            .code(authorizationCode)
            .client_id(GOOGLE_SNS_CLIENT_ID)
            .clientSecret(GOOGLE_SNS_CLIENT_SECRET)
            .redirect_uri(GOOGLE_SNS_CALLBACK_URL)
            .grant_type("authorization_code")
            .build()
        );

        log.info("google auth info");
        log.info(response.toString());

        return new Gson()
            .fromJson(
                response.getBody().toString(),
                SocialAuthResponse.class
            );
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        ResponseEntity<?> response = googleUserApi.getUserInfo(accessToken);

        log.info("google user response");
        log.info(response.toString());

        String jsonString = response.getBody().toString();

        Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
        .create();

        GoogleLoginResponse googleLoginResponse = gson.fromJson(jsonString, GoogleLoginResponse.class);

        return SocialUserResponse.builder()
            .id(googleLoginResponse.getId())
            .email(googleLoginResponse.getEmail())
            .build();
    }

    @Override
    public String getOauthRedirectURL() {

        Map<String,Object> params = new HashMap<>();

        params.put("scope",GOOGLE_DATA_ACCESS_SCOPE);
        params.put("response_type","code");
        params.put("client_id",GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri",GOOGLE_SNS_CALLBACK_URL);
        params.put("access_type", "offline");

        //parameter를 형식에 맞춰 구성해주는 함수
        String parameterString = params.entrySet().stream()
                .map(x->x.getKey()+"="+x.getValue())
                .collect(Collectors.joining("&"));
                
        String redirectURL=GOOGLE_SNS_LOGIN_URL+"?"+parameterString;
        System.out.println("redirectURL = " + redirectURL);

        return redirectURL;
    }

    
}
