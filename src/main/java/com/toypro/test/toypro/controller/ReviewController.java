package com.toypro.test.toypro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.component.Apikey;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {
    
    @Autowired
    Apikey apikey;

    @RequestMapping(value="/List",  method = RequestMethod.GET)
    public ModelAndView List (ModelAndView mav) throws Exception {
        
        mav.addObject("naverMap", apikey.naverMap());
        mav.setViewName("content/review/reviewList");

        return mav;
    }
}
