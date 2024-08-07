package com.toypro.test.toypro.dto.google;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoogleRequestAccessTokenDto {
    private String code;
    private String client_id;
    private String clientSecret;
    private String redirect_uri;
    private String grant_type;  
}
