package com.toypro.test.toypro.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {
    
    @RequestMapping(value="/",  method = RequestMethod.GET)
    public ModelAndView index (ModelAndView mav) throws Exception{
        

        mav.setViewName("content/main/index");
        
        return mav;
    }
}
