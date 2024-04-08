package com.toypro.test.toypro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.component.Apikey;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/review")
public class ReviewController {
    
    private final Apikey apikey;

    @RequestMapping(value="/List",  method = RequestMethod.GET)
    public ModelAndView List (ModelAndView mav) throws Exception {
        
        mav.addObject("naverMap", apikey.naverMap());
        mav.setViewName("content/review/reviewList");

        return mav;
    }
}
