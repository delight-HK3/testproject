package com.toypro.test.toypro.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.component.Apikey;
import com.toypro.test.toypro.dto.api.SchoolRequestDto;
import com.toypro.test.toypro.entity.gis.SchooleCodeEntity;
import com.toypro.test.toypro.service.gis.GisService;

import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping(value = "/gis")
public class GisController {

    @Autowired
    Apikey apikey;

    @Autowired
    private GisService gisService;

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

        List<SchooleCodeEntity> codeList = gisService.getCodeList();

        mav.addObject("codeList", codeList);
        mav.addObject("kakaoMap", apikey.kakaoMap());   // 카카오 맵
        mav.addObject("naverMap", apikey.naverMap());   // 네이버 맵 

        mav.setViewName("content/gis/clustering");

        return mav;
    }
    
    // 공공데이터를 활용한 전국 초,중,고 정보 가져오기
    @CrossOrigin("*")
    @ResponseBody
    @RequestMapping(value="/clusteringGps", method=RequestMethod.GET)
    public String requestMethodName(@ModelAttribute SchoolRequestDto searchDto) throws MalformedURLException, IOException, Exception {

        String addr = "http://api.data.go.kr/openapi/tn_pubr_public_elesch_mskul_lc_api?ServiceKey="; // 공공데이터 url
        String serviceKey = apikey.dataKey(); // 공공데이터 api 키값
        String parameter = "";

        // 초중고 학교 구분
        parameter = ("".equals(searchDto.getSearchSchoolSe())) ? parameter + "" : parameter + "&schoolSe=" + URLEncoder.encode(searchDto.getSearchSchoolSe(), "UTF-8");
        // 본교 / 분교 구분
        parameter = ("".equals(searchDto.getSearchBnhhSe())) ? parameter + "" : parameter + "&bnhhSe=" + URLEncoder.encode(searchDto.getSearchBnhhSe(), "UTF-8");
        // 출력할 학교의 수 
        parameter = parameter + "&numOfRows=" + searchDto.getSearchNumOfRows();
        // 교육청 구분
        parameter = ("0000000".equals(searchDto.getSearchCddcCd())) ? parameter + "" : parameter + "&cddcCode=" + searchDto.getSearchCddcCd();
        
        addr = addr + serviceKey + parameter; // api호출주소 + KEY + 작성한 파라미터

        HttpURLConnection conn = (HttpURLConnection) new URL(addr).openConnection();

        conn.connect();
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
        StringBuffer st = new StringBuffer();
        
        String line;
        while ((line = reader.readLine()) != null) {
            st.append(line);
        }
        
        JSONObject xmlJSONObj = XML.toJSONObject(st.toString());
        String jsonPrettyPrintString = xmlJSONObj.toString();

        return jsonPrettyPrintString;
    }

}
