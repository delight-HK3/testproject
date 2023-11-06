package com.toypro.test.toypro.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {
    
    @RequestMapping(value="/",  method = RequestMethod.GET)
    public ModelAndView index () throws Exception{
        ModelAndView mav = new ModelAndView();
        
        System.out.println("==========main==========");
        mav.setViewName("content/main/index");

        return mav;
    }
}
