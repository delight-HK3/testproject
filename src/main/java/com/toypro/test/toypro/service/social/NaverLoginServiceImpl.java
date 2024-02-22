package com.toypro.test.toypro.service.social;

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
import com.toypro.test.toypro.dto.naver.NaverLoginResponse;
import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.fegin.naver.NaverAuthApi;
import com.toypro.test.toypro.fegin.naver.NaverUserApi;
import com.toypro.test.toypro.type.UserType;
import com.toypro.test.toypro.utils.GsonLocalDateTimeAdapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component 
@Service
@RequiredArgsConstructor // 자동 의존성 주입
@Qualifier("naverLogin")
public class NaverLoginServiceImpl implements SocialLoginService{

    private final NaverAuthApi naverAuthApi;
    private final NaverUserApi naverUserApi;

    @Value("${spring.Oauth2.Naver.url}")
    private String NAVER_SNS_LOGIN_URL;

    @Value("${spring.OAuth2.Naver.client-id}")
    private String NAVER_SNS_CLIENT_ID;

    @Value("${spring.OAuth2.Naver.callback-url}")
    private String NAVER_SNS_CALLBACK_URL;

    @Value("${spring.OAuth2.Naver.client-secret}")
    private String NAVER_SNS_CLIENT_SECRET;


    @Override
    public UserType getServiceName() {
        return UserType.NAVER;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        ResponseEntity<?> response = naverAuthApi.getAccessToken(
        "authorization_code",
            NAVER_SNS_CLIENT_ID,
            NAVER_SNS_CLIENT_SECRET,
            authorizationCode,
            "state"
        );

        log.info("naver auth response {}");
        log.info(response.toString());

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

        ResponseEntity<?> response = naverUserApi.getUserInfo(headerMap);

        log.info("naver user response");
        log.info(response.toString());

        String jsonString = String.valueOf(response.getBody());

        Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
        .create();

        NaverLoginResponse naverLoginResponse = gson.fromJson(jsonString, NaverLoginResponse.class);
        NaverLoginResponse.Response naverUserInfo = naverLoginResponse.getResponse();

        return SocialUserResponse.builder()
            .id(naverUserInfo.getId())
            .nickname(naverUserInfo.getNickname())
            .birthday(naverUserInfo.getBirthday())
            .birthyear(naverUserInfo.getBirthyear())
            .age(naverUserInfo.getAge())
            .mobile(naverUserInfo.getMobile())
            .mobile_e164(naverUserInfo.getMobile_e164())
            .gender(naverUserInfo.getGender())
            .name(naverUserInfo.getName())
            .email(naverUserInfo.getEmail())
            .build();
    }

    @Override
    public String getOauthRedirectURL() {
        Map<String,Object> params = new HashMap<>();

        params.put("response_type","code");
        params.put("client_id",NAVER_SNS_CLIENT_ID);
        params.put("redirect_uri",NAVER_SNS_CALLBACK_URL);
        params.put("state","test");

        String parameterString = params.entrySet().stream()
                .map(x->x.getKey()+"="+x.getValue())
                .collect(Collectors.joining("&"));

        String redirectURL = NAVER_SNS_LOGIN_URL+"?"+parameterString;
        System.out.println(redirectURL);

        return redirectURL;
    }
    
}
