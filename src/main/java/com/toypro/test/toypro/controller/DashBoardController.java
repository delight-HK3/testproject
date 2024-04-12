package com.toypro.test.toypro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.service.dashboard.DashboardService;
import com.toypro.test.toypro.entity.dashboard.DashboardEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/dashboard")
public class DashBoardController {

    private final DashboardService dashboardService;

    @RequestMapping(value="/List",  method=RequestMethod.GET)
    public ModelAndView List (ModelAndView mav) throws Exception {
        
        List<DashboardEntity> boardList = dashboardService.searchList();
        
        mav.addObject("boardList", boardList);
        mav.setViewName("content/dashboard/dashboardList");

        return mav;
    }
}
