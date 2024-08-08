package com.toypro.test.toypro.dto.dashboard;

import com.toypro.test.toypro.entity.dashboard.DashboardCatgEntity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DashboardCatgDTO {

    private String catgCd;        // 게시판 카테고리 코드
    private String catgNm;        // 게시판 카테고리 이름

    // entity -> DTO (카테고리 목록)
    public static DashboardCatgDTO toCatgListDTO(DashboardCatgEntity entity){
        return DashboardCatgDTO.builder()
                                .catgCd(entity.getCatgCd())
                                .catgNm(entity.getCatgNm())
                                .build();
    }
}
