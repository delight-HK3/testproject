package com.toypro.test.toypro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.component.Apikey;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/gis")
public class GisController {

    @Autowired
    Apikey apikey;

    // gis 테스트
    @RequestMapping(value="/coordinate",  method = RequestMethod.GET)
    public ModelAndView coordinate (ModelAndView mav) throws Exception{

        mav.addObject("googleMap", apikey.googleMap());
        mav.setViewName("content/gis/coordinate");
        
        return mav;
    }

    // 클러스터 테스트
    @RequestMapping(value="/clustering", method=RequestMethod.GET)
    public ModelAndView clusterer (ModelAndView mav) throws Exception{

        mav.addObject("kakaoMap", apikey.kakaoMap());   // 카카오 맵
        //mav.addObject("googleMap", apikey.googleMap()); // 구글 맵
        mav.addObject("naverMap", apikey.naverMap());   // 네이버 맵 

        mav.setViewName("content/gis/clustering");

        return mav;
    }
    
    @CrossOrigin("*")
    @ResponseBody
    @RequestMapping(value="/clusteringGps", method=RequestMethod.POST)
    public String requestMethodName(@RequestParam String param) {
        return new String();
    }
    

}
