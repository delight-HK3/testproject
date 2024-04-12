package com.toypro.test.toypro.service.dashboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.entity.dashboard.DashboardEntity;

@Service
public interface DashboardService {

    List<DashboardEntity> searchList(); // 게시판 리스트 출력

    List<DashboardEntity> searchDetail(String boardCd); // 게시판 상세정보 출력
}
