package com.toypro.test.toypro.dto.google;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoogleLoginResponse {
    private String id;
    private String email;
    private String name;
    private String picture;
}