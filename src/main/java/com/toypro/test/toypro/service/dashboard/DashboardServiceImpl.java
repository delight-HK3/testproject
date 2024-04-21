package com.toypro.test.toypro.service.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.dashboard.DashboardDTO;
import com.toypro.test.toypro.entity.dashboard.DashboardEntity;
import com.toypro.test.toypro.repository.dashboard.DashboardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{

    private final DashboardRepository dashboardRepository;

    // 게시판 리스트 출력
    @Override
    public List<DashboardDTO> searchList() {
        
        List<DashboardEntity> boardlist = dashboardRepository.searchList();
        List<DashboardDTO> dtoList = new ArrayList<>();

        for(int i = 0; i < boardlist.size(); i++){
            DashboardDTO dto = DashboardDTO.toListDTO(boardlist.get(i));
            dtoList.add(dto);
        }

        return dtoList;
    
    }


    }
    
}
