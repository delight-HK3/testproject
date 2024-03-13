package com.toypro.test.toypro.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRequestDto {
    
    private String searchPageNo;      // 페이지 번호
    private String searchNumOfRows;   // 한 페이지 결과 수
    private String searchType;        // XML/JSON 여부
    private String searchSchoolNm;    // 학교명
    private String searchBnhhSe;      // 본교분교 구분
    private String searchCddcNm;      // 시도교육청명
    private String searchOperSttus;   // 운영상태
    private String searchEdcSport;    // 교육지원청코드

}