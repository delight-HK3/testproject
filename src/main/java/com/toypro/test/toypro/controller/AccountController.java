package com.toypro.test.toypro.controller;

/**
 * version 0.0.1
 * 최초 생성 : 2023/12/18
 * 설명 : SNS로그인 기능 컨트롤러 클래스
 */

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.toypro.test.toypro.dto.account.AccountRequestDto;
import com.toypro.test.toypro.dto.login.LoginRequest;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.service.UserService;
import com.toypro.test.toypro.service.account.AccountService;
import com.toypro.test.toypro.type.UserType;

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
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    /* 생성자 주입 part
    public AccountController(UserService userService, AccountService accountService, PasswordEncoder passwordEncoder){
        this.accountService = accountService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    */

    private String checkKey = ""; // 인증키 비교

    /**
     * SNS 로그인 - 유저 소셜 로그인 페이지 출력 
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
     * 일반 로그인
     * 
     * @param formInput
     * @param request
     * @param mav
     * @return
     */
    @PostMapping(value = "/normal")
    public ModelAndView normalLogin(LoginRequest formInput, HttpServletRequest request, ModelAndView mav) {
        String inputId = formInput.getInputId();
        String inputPass = formInput.getInputPass();

        HttpSession session = request.getSession(true); 
        SocialUserResponse userInfo = userService.doSocialLogin(UserType.NORMAL,inputId);

        if(passwordEncoder.matches(inputPass, userInfo.getPwd())){
            session.setAttribute("accessToken", userInfo.getAccessToken());
            session.setAttribute("no", userInfo.getNo());
            session.setAttribute("id", userInfo.getId());
            session.setAttribute("userType", userInfo.getSnsType());
            session.setAttribute("name", userInfo.getName());
            mav.addObject("message","");
        } else {
            mav.addObject("message","아이디 혹은 비밀번호가 맞지 않습니다.");
        }

        mav.addObject("usertype","NORMAL");
        mav.setViewName("content/sns/doneSnsLogin"); // 메인페이지로 이동
        
        return mav;        
    }

    /**
     * SNS 로그인 - 유저 소셜 로그인으로 리다이렉트 해주는 url
     * 
     * @param userType
     * @param code
     * @param request
     * @param mav
     * @return
     */
    @GetMapping(value = "/auth/{socialLoginType}/callback")
    public ModelAndView socialLogin(
            @PathVariable(name = "socialLoginType") UserType userType,
            @RequestParam(name = "code") String code,
            HttpServletRequest request,ModelAndView mav) {
        
        HttpSession session = request.getSession(true); 
        SocialUserResponse socialUserResponse = userService.doSocialLogin(userType, code);

        if(String.valueOf(socialUserResponse.getId()) != null){
            
            // sns 로그인시 DB에 저장
            accountService.snsSignin(socialUserResponse);
            
            session.setAttribute("accessToken", socialUserResponse.getAccessToken());
            session.setAttribute("id", socialUserResponse.getId());
            session.setAttribute("no", accountService.searchUserNo(socialUserResponse.getId()));
            session.setAttribute("userType", socialUserResponse.getSnsType());
            session.setAttribute("name", socialUserResponse.getName());
        }
        mav.addObject("message","SNS_LOGIN");
        mav.addObject("usertype","SNS");
        mav.setViewName("content/sns/doneSnsLogin"); // 메인페이지로 이동
        
        return mav;        
    }

    /**
     * 회원가입 - 로그아웃버튼 기능
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
     * 회원가입 - 중복 닉네임 체크
     * 
     * @param accountRequestDto
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping(value = "/nickNameCheck", method=RequestMethod.GET)
    public String nickNameCheck(@ModelAttribute AccountRequestDto accountRequestDto) throws IOException, ServletException {
        
        String check = accountService.searchNickName(accountRequestDto.getUserNickName());
        String request = ("T".equals(check)) ? "SUCCESS" : "ALREADY_NICK";
        
        return request;
    }

    /**
     * 회원가입 - 중복 아이디 체크
     * 
     * @param signinRequestDto
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping(value = "/idCheck", method=RequestMethod.GET)
    public String idCheck(@ModelAttribute AccountRequestDto accountRequestDto) throws IOException, ServletException {

        // 중복 아이디가 없으면 F, 있으면 T
        String check = accountService.searchUser(accountRequestDto.getUserId());
        String request = ("T".equals(check)) ? "SUCCESS" : "ALREADY_USER";
        
        return request;
    }
    
    /**
     * 이메일 인증 - 이메일 보내기
     * 
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/CheckMail", method=RequestMethod.POST)
    public void mailConfirm(@RequestParam("email") String email) throws Exception {
        
        // 메일 전송 및 인증키 저장
        checkKey = accountService.mailConfirm(email);

    }

    /**
     * 이메일 인증 - 타이머가 0가 된경우 인증번호 초기화
     * 
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/resetCheckNum", method=RequestMethod.GET)
    public void resetCheckNum() throws Exception {
        checkKey = "";
    }

    /**
     * 회원가입 - 입력한 신규 회원 정보 입력 
     * 
     * @param signinRequestDto
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping(value = "/signin", method=RequestMethod.GET)
    public String signin (@ModelAttribute AccountRequestDto accountRequestDto) throws IOException, ServletException {
        
        String check = accountService.signin(accountRequestDto, checkKey);
        
        return check;
    }

}
