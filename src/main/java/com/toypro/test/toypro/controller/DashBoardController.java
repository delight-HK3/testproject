package com.toypro.test.toypro.controller;

import java.io.IOException;
import java.text.ParseException;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/11
 * 설명 : 게시판 컨트롤러 클래스
 */

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.service.dashboard.DashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.toypro.test.toypro.dto.dashboard.DashboardCatgDTO;
import com.toypro.test.toypro.dto.dashboard.DashboardDTO;
import com.toypro.test.toypro.dto.dashboard.DashboardSaveDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dashboard")
public class DashBoardController {

    private final DashboardService dashboardService;

    /**
     * 게시판 - 게시글 목록 페이지
     * 
     * @param mav
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/List",  method=RequestMethod.GET)
    public ModelAndView dashBoardList (ModelAndView mav) throws Exception {
        
        List<DashboardDTO> boardList = dashboardService.searchList();
        
        mav.addObject("boardList", boardList);
        mav.setViewName("content/dashboard/dashboardList");

        return mav;
    }

    /**
     * 게시판 - 신규 게시글 추가 페이지
     * 
     * @param mav
     * @return
     */
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView dashBoardAdd (ModelAndView mav, HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        String snsType = String.valueOf(session.getAttribute("userType"));
        
        List<DashboardCatgDTO> catgList = dashboardService.userCatg(snsType);

        mav.addObject("catgList", catgList);
        mav.setViewName("content/dashboard/dashboardAdd");

        return mav;
    }

    /**
     * 게시판 - 신규 게시글 추가
     * 
     * @param dashboardSaveDTO
     * @throws IOException
     * @throws ServletException
     * @throws ParseException 
     */
    @ResponseBody
    @RequestMapping(value = "/save", method=RequestMethod.GET) 
    public String dashboardSave (@ModelAttribute DashboardSaveDTO dashboardSaveDTO, HttpServletRequest request) throws IOException, ServletException, ParseException {
        
        HttpSession session = request.getSession(false);
        int userNo = (int) session.getAttribute("no");

        dashboardService.dashboardSave(dashboardSaveDTO, userNo);

        return "SUCCESS";
    }

    /**
     * 게시판 - 게시글 상세보기 페이지
     * 
     * @param mav
     * @param boardCd
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/details",  method=RequestMethod.GET)
    public ModelAndView dashboardDetails (ModelAndView mav, @RequestParam("boardCd") String boardCd) throws Exception {

        dashboardService.detailCntUp(boardCd); // 조회수 1증가
        DashboardDTO detail = dashboardService.searchDetail(boardCd);

        mav.addObject("detail", detail);
        mav.setViewName("content/dashboard/dashboardDetail");

        return mav;
    }
    
    /**
     * 게시판 - 기존 게시글 수정 페이지
     * 
     * @param mav
     * @return
     */
    @RequestMapping(value="/Edit", method=RequestMethod.GET)
    public ModelAndView dashBoardEdit (ModelAndView mav, @RequestParam("boardCd") String boardCd, HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        String snsType = String.valueOf(session.getAttribute("userType"));
        List<DashboardCatgDTO> catgList = dashboardService.userCatg(snsType);

        DashboardDTO detail = dashboardService.searchDetail(boardCd);

        mav.addObject("catgList", catgList);
        mav.addObject("detail", detail);
        mav.setViewName("content/dashboard/dashboardEdit");

        return mav;
    }
}
