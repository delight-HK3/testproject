package com.toypro.test.toypro.service.dashboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.entity.dashboard.DashboardEntity;
import com.toypro.test.toypro.repository.dashboard.DashboardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{

    private final DashboardRepository dashboardRepository;

    // 게시판 리스트 출력
    @Override
    public List<DashboardEntity> searchList() {
        
        List<DashboardEntity> boardlist = dashboardRepository.searchList();

        return boardlist;
    
    }

    // 게시판 상세정보 출력
    @Override
    public DashboardEntity searchDetail(String boardCd) {
        DashboardEntity detail = dashboardRepository.searchdetail(boardCd);

        return detail;
    }

    // 게시글 조회수 조회
    @Override
    public int searchViewCnt(String boardCd) {
        int cnt = dashboardRepository.searchViewCnt(boardCd);

        return cnt;
    }

    // 게시글 들어 갈 때 마다 1 증가
    @Override
    public void detailCntUp(String boardCd, int cnt) {
        dashboardRepository.detailCntUp(boardCd, cnt);
    }
    
}
