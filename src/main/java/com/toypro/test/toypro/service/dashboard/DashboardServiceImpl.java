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

    @Override
    public List<DashboardEntity> searchList() {
        
        List<DashboardEntity> boardlist = dashboardRepository.searchList();

        return boardlist;
    
    }
    
}
