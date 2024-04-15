package com.toypro.test.toypro.entity.dashboard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name="t_bd_table")
public class DashboardEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private int seq;
    
    @Column(name = "BOARD_CD")
    private String boardCd;

    @Column(name = "BOARD_TITLE")
    private String boardTitle;

    @Column(name = "BOARD_SUBJECT")
    private String boardSubject;

    @Column(name = "BOARD_SUBJECT_NM")
    private String boardSubjectNm;

    @Column(name = "CATG_NM")
    private String catgNm;

    @Column(name = "BOARD_CNT")
    private int boardCnt;

    @Column(name = "REG_DATE")
    private String regDate;

}
