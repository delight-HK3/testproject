package com.toypro.test.toypro.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {
    
    @Value("${naver.api.clientID}")
    private String clientID;

    @RequestMapping(value="/",  method = RequestMethod.GET)
    public ModelAndView index (ModelAndView mav) throws Exception{
        
        // System.out.println("clientID : "+clientID);

        mav.addObject("clientID", clientID);
        mav.setViewName("content/main/index");
        
        return mav;
    }
}
