package com.toypro.test.toypro.controller;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/11
 * 설명 : 게시판 컨트롤러 클래스
 */

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.service.dashboard.DashboardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.toypro.test.toypro.dto.dashboard.DashboardDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dashboard")
public class DashBoardController {

    private final DashboardService dashboardService;

    /**
     * 게시판 - 게시글 목록
     * 
     * @param mav
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/List",  method=RequestMethod.GET)
    public ModelAndView List (ModelAndView mav) throws Exception {
        
        List<DashboardDTO> boardList = dashboardService.searchList();
        
        mav.addObject("boardList", boardList);
        mav.setViewName("content/dashboard/dashboardList");

        return mav;
    }

    /**
     * 
     * 
     * @param mav
     * @return
     */
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView requestMethodName(ModelAndView mav, HttpServletRequest request) {
        
        HttpSession session = request.getSession(true);
        String snsType = String.valueOf(session.getAttribute("snsType"));
        

        mav.setViewName("content/dashboard/dashboardAdd");

        return mav;
    }
    

    /**
     * 게시판 - 게시글 상세보기
     * 
     * @param mav
     * @param boardCd
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/details",  method=RequestMethod.GET)
    public ModelAndView details (ModelAndView mav, @RequestParam("boardCd") String boardCd) throws Exception {
        
        // 현재 게시글의 조회수 정보를 가져오고 조회수 1 증가
        int cnt = dashboardService.searchViewCnt(boardCd);
        cnt++; 
        dashboardService.detailCntUp(boardCd, cnt);

        DashboardDTO detail = dashboardService.searchDetail(boardCd);
        
        mav.addObject("detail", detail);
        mav.setViewName("content/dashboard/dashboardDetail");

        return mav;
    }
}
