package com.toypro.test.toypro.service.social;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : url을 구성해 service로 넘겨주면 service->controller를 
 * 거쳐 해당 url을 통해서 소셜 로그인 페이지로 리다이렉트 시켜주는 클래스
 */

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth{

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

    /**
     * https://accounts.google.com/o/oauth2/v2/auth?scope=profile&response_type=code
     * &client_id="할당받은 id"&redirect_uri="access token 처리")
     * 로 Redirect URL을 생성하는 로직을 구성
     */
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
    
    @Override
    public String requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();

        params.put("code", code);
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_REQUEST_URL,
                params,String.class);

        if(responseEntity.getStatusCode()== HttpStatus.OK){
            return responseEntity.getBody();
        }

        return "구글 로그인 요청 처리 실패";
    }

    /*
    @Override
    public String requestUserInfo(String access_token) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();

        params.put("authorization", "Bearer " + access_token);
        
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_ACCESS_TOKEN_URL, 
                params, String.class);

        if(responseEntity.getStatusCode()== HttpStatus.OK){
            return responseEntity.getBody();
        }

        return "구글 개인정보 요청 처리 실패";
    }*/
    
}
