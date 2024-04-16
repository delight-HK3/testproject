package com.toypro.test.toypro.dto.dashboard;

import java.text.SimpleDateFormat;

import com.toypro.test.toypro.entity.dashboard.DashboardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    
    private int seq;
    private String boardCd;
    private String boardTitle;
    private String boardUserNm;
    private String catgNm;
    private String boardSubject;
    private String boardNickName;
    private int boardCnt;
    private String regDate;
    private String updtDate;

    // entity -> DTO 
    public static DashboardDTO toListDTO(DashboardEntity entity){
        SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
        return DashboardDTO.builder()
                        .catgNm(entity.getCatgNm())
                        .boardCd(entity.getBoardCd())
                        .boardTitle(entity.getBoardTitle())
                        .boardNickName(entity.getNickName())
                        .regDate(newDtFormat.format(entity.getRegDate())) // 날짜 포맷
                        .boardCnt(entity.getBoardCnt())
                        .build();
    }
}
