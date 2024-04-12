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
    public List<DashboardEntity> searchDetail(String boardCd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchDetail'");
    }
    
}
