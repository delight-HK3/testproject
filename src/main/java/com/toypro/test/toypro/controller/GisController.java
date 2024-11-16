package com.toypro.test.toypro.controller;

/**
 * version 0.0.1
 * 최초 생성 : 2024/03/28
 * 설명 : 지도 관련 컨트롤러 
 */

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

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.component.Apikey;
import com.toypro.test.toypro.dto.api.SchoolRequestDto;
import com.toypro.test.toypro.entity.gis.SchooleCodeEntity;
import com.toypro.test.toypro.repository.gis.SchooleCodeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequiredArgsConstructor // 생성자 주입
@RequestMapping(value = "/gis")
public class GisController {

    private final Apikey apikey;
    private final SchooleCodeRepository schooleCodeRepository; 

    /**
     * 구글지도를 활용한 좌표 획득
     * 
     * @param mav
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/coordinate",  method = RequestMethod.GET)
    public ModelAndView coordinate (ModelAndView mav) throws Exception{

        mav.addObject("googleMap", apikey.googleMap());
        mav.setViewName("content/gis/coordinate");
        
        return mav;
    }

    /**
     * 데이터 클러스터링 - 지도표출
     * 
     * @param mav
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/clustering", method=RequestMethod.GET)
    public ModelAndView clusterer (ModelAndView mav) throws Exception{

        List<SchooleCodeEntity> codeList = schooleCodeRepository.findAll();
        
        mav.addObject("codeList", codeList);
        mav.addObject("kakaoMap", apikey.kakaoMap());   // 카카오 맵
        mav.addObject("naverMap", apikey.naverMap());   // 네이버 맵 

        mav.setViewName("content/gis/clustering");

        return mav;
    }
    
    /**
     * direction 5 구현
     * 
     * @param mav
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/direction", method=RequestMethod.GET)
    public ModelAndView direction (ModelAndView mav) throws Exception{

        mav.addObject("naverMap", apikey.naverMap());   // 네이버 맵 

        mav.setViewName("content/gis/direction");

        return mav;
    }

    /**
     * 데이터 클러스터링 - 공공데이터를 활용한 전국 초,중,고 정보 가져오기
     * 
     * @param searchDto
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    @CrossOrigin("*")
    @ResponseBody
    @RequestMapping(value="/clusteringGps", method=RequestMethod.GET)
    public String requestMethodName(@ModelAttribute SchoolRequestDto searchDto) throws MalformedURLException, IOException, Exception {

        String addr = "http://api.data.go.kr/openapi/tn_pubr_public_elesch_mskul_lc_api?ServiceKey="; // 공공데이터 url
        String serviceKey = apikey.dataKey(); // 공공데이터 api 키값
        String parameter = "";

        // 초중고 학교 구분
        parameter = ("".equals(searchDto.getSearchSchoolSe())) ? parameter + "" : parameter + "&schoolSe=" + URLEncoder.encode(searchDto.getSearchSchoolSe(), "UTF-8");
        // 본교/분교 구분
        parameter = ("".equals(searchDto.getSearchBnhhSe())) ? parameter + "" : parameter + "&bnhhSe=" + URLEncoder.encode(searchDto.getSearchBnhhSe(), "UTF-8");
        // 교육청 구분
        parameter = ("0000000".equals(searchDto.getSearchCddcCd())) ? parameter + "" : parameter + "&cddcCode=" + searchDto.getSearchCddcCd();
        // 공립/사립 구분
        parameter = ("".equals(searchDto.getSearchFondType())) ? parameter + "" : parameter + "&fondType=" + URLEncoder.encode(searchDto.getSearchFondType(), "UTF-8");

        // 출력할 학교의 수 
        parameter = parameter + "&numOfRows=" + searchDto.getSearchNumOfRows();

        addr = addr + serviceKey + parameter; // api호출주소 + KEY + 작성한 파라미터

        HttpURLConnection conn = (HttpURLConnection) new URL(addr).openConnection();

        conn.connect();
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
        StringBuffer st = new StringBuffer();
        
        String line;
        while ((line = reader.readLine()) != null) {
            st.append(line);
        }
        
        JSONObject xmlJSONObj = XML.toJSONObject(st.toString());
        String jsonPrettyPrintString = xmlJSONObj.toString();

        return jsonPrettyPrintString;
    }

    @ResponseBody
    @RequestMapping(value="/directionGps", method=RequestMethod.GET)
    public String directionGps() throws Exception {
        String CLIENT_ID = "vg485bk4yf"; // Replace with your Client ID
        String CLIENT_SECRET = "nCQUgvN69HnQwdFyOnMMfaNurmCmMtymqaRGluHv"; // Replace with your Client Secret
        String API_URL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";

        String start = "126.9780,37.5665"; // Example: Seoul City Hall
        String goal = "127.057427,37.511453";

        String url = API_URL + "?start=" + start + "&goal=" + goal + "&option=trafast&waypoints=127.007542,37.492015|127.033690,37.484784";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
        connection.setRequestProperty("X-NCP-APIGW-API-KEY", CLIENT_SECRET);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            return response.toString();
        } else {
            throw new Exception("HTTP error code: " + responseCode + ", " + connection.getResponseMessage());
        }
    }

}
