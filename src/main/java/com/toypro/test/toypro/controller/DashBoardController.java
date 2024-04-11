package com.toypro.test.toypro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/dashboard")
public class DashBoardController {

    @RequestMapping(value="/List",  method = RequestMethod.GET)
    public ModelAndView List (ModelAndView mav) throws Exception {
        
        mav.setViewName("content/review/reviewList");

        return mav;
    }
}
