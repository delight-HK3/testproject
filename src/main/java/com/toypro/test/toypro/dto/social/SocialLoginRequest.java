package com.toypro.test.toypro.dto.social;


import com.toypro.test.toypro.type.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SocialLoginRequest {
    @NotNull
    private UserType userType;
    @NotNull
    private String code;
}
