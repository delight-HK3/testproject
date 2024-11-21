package com.toypro.test.toypro.entity.dashboard;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="t_bd_table")
public class DashboardSaveEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;                // 일련번호

    @Column(name="BOARD_CD")
    private String boardCd;         // 게시글코드

    @Column(name="BOARD_TITLE")
    private String boardTitle;      // 게시글제목

    @Column(name="BOARD_USER_NO")
    private int boardUserNo;      // 게시글 작성자 번호

    @Column(name="BOARD_SUBJECT")
    private String boardSubject;    // 게시글 내용

    @Column(name="BOARD_CNT")
    private int boardCnt;           // 게시글 조회수
    
    @Column(name="REG_DATE")
    private Date regDate;           // 게시글 작성일

    @Column(name="UPDT_DATE")
    private Date updtDate;          // 게시글 수정일

    @Column(name="BOARD_CATG_CD")
    private String catgCd;          // 게시글 카테고리 코드

}
