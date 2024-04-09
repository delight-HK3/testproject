package com.toypro.test.toypro.controller;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : SNS로그인 기능 컨트롤러 클래스
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

import com.toypro.test.toypro.dto.signin.SigninRequestDto;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.entity.signin.SigninEntity;

import com.toypro.test.toypro.repository.signin.SignupRepository;
import com.toypro.test.toypro.service.UserService;
import com.toypro.test.toypro.type.UserType;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app/accounts")
public class AccountController {
    
    private final UserService userService;
    private final SignupRepository signupRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 유저 소셜 로그인 페이지 출력 
     * 
     * @param userType
     * @throws IOException
     */
    @GetMapping(value = "/auth/{socialLoginType}")
    public void socialLoginType(@PathVariable(name="socialLoginType") UserType userType) throws IOException {

        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", userType);
        userService.request(userType);
    }

    /**
     * 유저 소셜 로그인으로 리다이렉트 해주는 url
     * 
     * @param SocialLoginPath
     * @throws IOException
     * @return void
     */
    @GetMapping(value = "/auth/{socialLoginType}/callback")
    public ModelAndView socialLogin(
            @PathVariable(name = "socialLoginType") UserType userType,
            @RequestParam(name = "code") String code,
            HttpServletRequest request) {
        
        HttpSession session = request.getSession(true); 
        ModelAndView mav = new ModelAndView();
        
        SocialUserResponse socialUserResponse = userService.doSocialLogin(userType, code);
        
        if(String.valueOf(socialUserResponse.getId()) != null){
            session.setAttribute("snsType", socialUserResponse.getSnsType());
            session.setAttribute("accessToken", socialUserResponse.getAccessToken());
            session.setAttribute("id", socialUserResponse.getId());
            session.setAttribute("name", socialUserResponse.getName());
        }

        mav.setViewName("content/sns/doneSnsLogin");
        
        return mav;        
    }
    
    /**
     * 로그아웃버튼 기능
     * 
     * @param mav
     * @return
     */
    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request, ServletResponse response) throws IOException, ServletException {
        
        HttpSession session = request.getSession(true); 

        String snsType = (String) session.getAttribute("snsType");

        if(!"null".equals(snsType)){
            session.invalidate();
        }

        response.setContentType("text/html; charset=utf-8");
                    PrintWriter w = response.getWriter();
                    w.write("<script>location.href='/';</script>");
                    w.flush();
                    w.close();

    }
    
    /**
     * 중복 아이디 체크
     * 
     * @param signinRequestDto
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping(value = "/idCheck", method=RequestMethod.GET)
    public String idCheck(@ModelAttribute SigninRequestDto signinRequestDto) throws IOException, ServletException {

        // 중복 아이디가 없으면 F, 있으면 T
        String check = signupRepository.searchUser(signinRequestDto.getUserId());

        if("T".equals(check)){
            return "AREADY_USER";
        } else {
            return "SUCCESS";
        }

    }
    
    /**
     * 이메일 보내기
     * 
     * @param email
     * @return
     */
    @ResponseBody
    @PostMapping("/CheckMail")
    public String mailConfirm(@RequestParam("email") String email) throws Exception {
        
        //=========================== key 발급 =========================//
        Random random=new Random();  //난수 생성을 위한 랜덤 클래스
		String key="";  //인증번호 

		//입력 키를 위한 코드
		for(int i =0; i<3;i++) {
			int index=random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
			key+=(char)index;
		}
		int numIndex=random.nextInt(9999)+1000; //4자리 랜덤 정수를 생성
		key+=numIndex;
        //=========================== key 발급 =========================//

        try {   
            
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messagehelper = new MimeMessageHelper(message, true);

            messagehelper.setTo(email);
            messagehelper.setFrom("dabin49140@gmail.com", "MyTest");
            messagehelper.setSubject("[myTest 인증메일 입니다]");
            messagehelper.setText(
                    "<h1>myTest 메일인증</h1>" +
                    "<br>myTest에 오신 것을 환영합니다." +
                    "<br>아래 입력값을 인증번호 입력폼에 입력하시기 바랍니다." +
                    "<br><br>" + key
            ,true);

            // 회원가입 이메일 발송
            javaMailSender.send(message);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return key;
    }

    /**
     * 입력한 신규 회원 정보 입력 
     * 
     * @param signinRequestDto
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping(value = "/signin", method=RequestMethod.GET)
    public void signin (@ModelAttribute SigninRequestDto signinRequestDto) throws IOException, ServletException {

        SigninEntity signinEntity = SigninEntity.builder()
                                        .userId(signinRequestDto.getUserId())
                                        .userEmail(signinRequestDto.getUserEmail())
                                        .userName(signinRequestDto.getUserName())
                                        .userPwd(signinRequestDto.getUserPwd())
                                        .phoneNo(signinRequestDto.getUserPhone()).build();

        signupRepository.save(signinEntity);
    }
}
