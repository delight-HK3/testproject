package com.toypro.test.toypro.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolResponseDto {
    
    private String schoolNm;
    private String schoolSe;
    private String fondDate;
    private String operSttus;
    private String lnmadr;

    private String latitude;
    private String longitude;
}
