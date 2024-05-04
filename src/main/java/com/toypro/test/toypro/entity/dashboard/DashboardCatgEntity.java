package com.toypro.test.toypro.entity.dashboard;

import java.util.Date;

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
@Entity(name="t_bd_catg")
public class DashboardCatgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;                // 일련번호

    @Column(name="CATG_CD")
    private String catgCd;          // 게시글 카테고리 코드

    @Column(name="CATG_NM")
    private String catgNm;          // 게시글 카테고리 이름

    @Column(name="REG_DATE")
    private Date regDate;           // 게시글 작성일

    @Column(name="UPDT_DATE")
    private Date updtDate;          // 게시글 수정일
    
}
