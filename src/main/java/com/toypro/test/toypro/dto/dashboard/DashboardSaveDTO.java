package com.toypro.test.toypro.dto.dashboard;

import lombok.Data;

@Data
public class DashboardSaveDTO {
    
    private int seq;              // 게시판 게시글 일련번호
    private String boardCd;       // 게시판 게시글 코드
    private String boardTitle;    // 게시판 게시글 제목
    private String regDate;       // 게시판 게시글 등록일
    private String updtDate;      // 게시판 게시글 수정일
    private String catgCd;        // 게시판 카테고리 코드
    private String boardSubject;  // 게시판 내용

}
