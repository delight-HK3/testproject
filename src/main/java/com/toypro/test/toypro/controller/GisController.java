package com.toypro.test.toypro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.component.Apikey;

@RestController
@RequestMapping(value = "/gis")
public class GisController {

    @Autowired
    Apikey apikey;

    @RequestMapping(value="/coordinate",  method = RequestMethod.GET)
    public ModelAndView index (ModelAndView mav) throws Exception{

        mav.addObject("googleMap", apikey.googleMap());
        mav.setViewName("content/gis/coordinate");
        
        return mav;
    }
}
