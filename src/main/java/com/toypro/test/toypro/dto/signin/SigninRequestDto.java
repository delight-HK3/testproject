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
    
    private String userName;
    private String userId;
    private String userPwd;
    private String userEmail;
    private String userPhone;
    
}
