package com.toypro.test.toypro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    
    @RequestMapping(value="/",  method = RequestMethod.GET)
    public ModelAndView index (ModelAndView mav, HttpServletRequest request) throws Exception{
        
        mav.setViewName("content/main/index");
        
        return mav;
    }
}
