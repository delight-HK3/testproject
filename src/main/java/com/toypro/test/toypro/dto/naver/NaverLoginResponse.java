package com.toypro.test.toypro.dto.naver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaverLoginResponse {
    @Builder.Default
    private Response response = Response.builder().build();

    private String resultCode; // 결과코드
    private String message; // 메세지

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String email;
        private String nickname;
        private String name;
        private String age;
        private String gender;
        private String birthyear;
        private String birthday;
        private String profile_image;
        private String mobile;
        private String mobile_e164;
    }
}
