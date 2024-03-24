package com.toypro.test.toypro.entity.gis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="t_cddc_nm")
public class SchooleCodeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private int seq;

    @Column(name = "CDDC_CD")
    private String cddcCd;

    @Column(name = "CDDC_NM")
    private String cddcNm;

    @Builder
    public void codeList(int seq, String cddcCd, String cddcNm){
        this.seq = seq;
        this.cddcCd = cddcCd;
        this.cddcNm = cddcNm;
    }
}   
