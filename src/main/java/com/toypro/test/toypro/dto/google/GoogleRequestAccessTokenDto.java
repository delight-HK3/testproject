package com.toypro.test.toypro.dto.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleRequestAccessTokenDto {
    private String code;
    private String client_id;
    private String clientSecret;
    private String redirect_uri;
    private String grant_type;  
}
