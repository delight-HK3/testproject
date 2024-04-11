package com.toypro.test.toypro.dto.signin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequestDto {
    
    private String userChecknum; // 인증번호

    private String userName;  // 입력한 이름
    private String userId;    // 입력한 아이디
    private String userPwd;   // 입력한 비밀번호
    private String userEmail; // 입력한 이메일
    private String userPhone; // 입력한 전화번호
    
}
