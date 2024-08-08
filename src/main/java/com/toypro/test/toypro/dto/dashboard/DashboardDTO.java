package com.toypro.test.toypro.dto.dashboard;

import java.text.SimpleDateFormat;

import com.toypro.test.toypro.entity.dashboard.DashboardEntity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DashboardDTO {
    
    private int seq;              // 게시판 일련번호
    private String boardCd;       // 게시판 게시글 코드
    private String boardTitle;    // 게시판 게시글 제목
    private int boardUserNo;      // 게시판 사용자 번호
    private String catgNm;        // 게시판 카테고리 이름
    private String boardSubject;  // 게시판 내용
    private String boardNickName; // 게시판 게시자 닉네임
    private int boardCnt;         // 게시판 게시글 조회수
    private String regDate;       // 게시판 게시글 등록일
    private String updtDate;      // 게시판 게시글 수정일

    // entity -> DTO (게시글 목록보기)
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

    // entity -> DTO (게시글 상세보기)
    public static DashboardDTO toDetailDTO(DashboardEntity entity){
        SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
        return DashboardDTO.builder()
                        .catgNm(entity.getCatgNm())
                        .boardCd(entity.getBoardCd())
                        .boardSubject(entity.getBoardSubject())
                        .boardUserNo(entity.getBoardUserNo())
                        .boardTitle(entity.getBoardTitle())
                        .boardNickName(entity.getNickName())
                        .regDate(newDtFormat.format(entity.getRegDate())) // 날짜 포맷
                        .boardCnt(entity.getBoardCnt())
                        .build();
    }
}
