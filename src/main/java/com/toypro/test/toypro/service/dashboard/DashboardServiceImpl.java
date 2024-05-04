package com.toypro.test.toypro.service.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.dashboard.DashboardCatgDTO;
import com.toypro.test.toypro.dto.dashboard.DashboardDTO;
import com.toypro.test.toypro.entity.dashboard.DashboardCatgEntity;
import com.toypro.test.toypro.entity.dashboard.DashboardEntity;
import com.toypro.test.toypro.repository.dashboard.DashboardCatgRepository;
import com.toypro.test.toypro.repository.dashboard.DashboardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{

    private final DashboardRepository dashboardRepository;
    private final DashboardCatgRepository dashboardCatgRepository;

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

    // 게시판 상세정보 출력
    @Override
    public DashboardDTO searchDetail(String boardCd) {
        DashboardEntity detail = dashboardRepository.searchdetail(boardCd);
        DashboardDTO detailDTO = DashboardDTO.toDetailDTO(detail);
        
        return detailDTO;
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

    // 게시글 카테고리 목록 확인
    @Override
    public List<DashboardCatgDTO> userCatg(String snsType) {

        List<DashboardCatgEntity> catgList = new ArrayList<>();
        List<DashboardCatgDTO> dtoList = new ArrayList<>();

        if("Admin".equals(snsType)){
            catgList = dashboardCatgRepository.findAll();
        } else {
            catgList = dashboardCatgRepository.userCatg();
        }

        for(int i = 0; i < catgList.size(); i++){
            DashboardCatgDTO dto = DashboardCatgDTO.toCatgListDTO(catgList.get(i));
            dtoList.add(dto);
        }

        return dtoList;
    }
    
}
