package com.toypro.test.toypro.account.social;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth{
    // 소셜 로그인 타입별 클래스

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

    private final ObjectMapper objectMapper;

    @Override
    public String getOauthRedirectURL() {

        Map<String,Object> params=new HashMap<>();
        params.put("scope",GOOGLE_DATA_ACCESS_SCOPE);
        params.put("response_type","code");
        params.put("client_id",GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri",GOOGLE_SNS_CALLBACK_URL);
        
        //parameter를 형식에 맞춰 구성해주는 함수
        String parameterString = params.entrySet().stream()
                .map(x->x.getKey()+"="+x.getValue())
                .collect(Collectors.joining("&"));
        String redirectURL=GOOGLE_SNS_LOGIN_URL+"?"+parameterString;
        System.out.println("redirectURL = " + redirectURL);

        return redirectURL;

     /* *
        * https://accounts.google.com/o/oauth2/v2/auth?scope=profile&response_type=code
        * &client_id="할당받은 id"&redirect_uri="access token 처리")
        * 로 Redirect URL을 생성하는 로직을 구성
        * */
    }
    
}
