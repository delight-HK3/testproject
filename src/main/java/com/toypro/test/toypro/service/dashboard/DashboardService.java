package com.toypro.test.toypro.service.dashboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.entity.dashboard.DashboardEntity;

@Service
public interface DashboardService {

    List<DashboardEntity> searchList(); // 게시판 리스트 출력

    DashboardEntity searchDetail(String boardCd); // 게시판 상세정보 출력

    int searchViewCnt(String boardCd); // 게시글 조회수

    void detailCntUp(String boardCd, int cnt); // 게시글 조회수 1증가
}
