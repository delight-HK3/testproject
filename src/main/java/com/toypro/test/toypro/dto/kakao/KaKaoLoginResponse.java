package com.toypro.test.toypro.dto.kakao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KaKaoLoginResponse {
    private String id;
    
    @Builder.Default
    private KakaoLoginData kakao_account = KakaoLoginData.builder().build();

    @Builder
    @Data
    public static class KakaoLoginData {
        private String gender;
        private String email;

        @Builder.Default
        private KakaoProfile profile = KakaoProfile.builder().build();
        
        @Builder.Default
        private KakaoPropery properties = KakaoPropery.builder().build();
        
        @Builder
        @Data
        public static class KakaoProfile {
            private String nickname;
        }

        @Builder
        @Data
        public static class KakaoPropery {
            private String nickname;
        }
    }
}
