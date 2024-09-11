package com.toypro.test.toypro.service.dashboard;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.dashboard.DashboardCatgDTO;
import com.toypro.test.toypro.dto.dashboard.DashboardDTO;
import com.toypro.test.toypro.dto.dashboard.DashboardSaveDTO;

@Service
public interface DashboardService {

    List<DashboardDTO> searchList(); // 게시판 리스트 출력

    DashboardDTO searchDetail(String boardCd); // 게시판 상세정보 출력

    int searchViewCnt(String boardCd); // 게시글 조회수

    void detailCntUp(String boardCd); // 게시글 조회수 1증가

    void dashboardSave(DashboardSaveDTO dashboardSaveDTO, int userNo) throws ParseException; // 게시글 저장

    void dashboardEdit(DashboardSaveDTO dashboardSaveDTO) throws ParseException; // 게시글 수정

    void dashboardDelete(DashboardDTO dashboardDTO); // 게시글 삭제

    List<DashboardCatgDTO> userCatg(String snsType); // 게시글 목록
}
