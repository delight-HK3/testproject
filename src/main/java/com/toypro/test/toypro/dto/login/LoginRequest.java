package com.toypro.test.toypro.dto.login;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    private String inputId;
    private String inputPass;
}
