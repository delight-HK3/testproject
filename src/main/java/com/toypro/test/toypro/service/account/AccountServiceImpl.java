package com.toypro.test.toypro.service.account;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.toypro.test.toypro.repository.signin.SignupRepository;
import com.toypro.test.toypro.dto.signin.SigninRequestDto;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.entity.signin.SigninEntity;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final JavaMailSender javaMailSender;
    private final SignupRepository signupRepository;

    // 이메일 인증
    @Override
    public String mailConfirm(String email) {
       
        //=========================== key 발급 =========================//
        Random random=new Random();  //난수 생성을 위한 랜덤 클래스
        String key="";  //인증번호 
 
        //입력 키를 위한 코드
        for(int i =0; i<3;i++) {
            int index=random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
            key+=(char)index;
        }
        int numIndex=random.nextInt(9999)+1000; //4자리 랜덤 정수를 생성
        key+=numIndex; // 메일 전송용 
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

    // 회원정보 DB에 입력
    @Override
    public String signin(SigninRequestDto signinRequestDto, String checkKey) {

        String userCheckNum = signinRequestDto.getUserChecknum();

        if(!checkKey.equals(userCheckNum)){
            return "FAILED";
        } else {
            
            Date date = new Date(); // 오늘 날짜

            SigninEntity signinEntity = SigninEntity.builder()
                                        .userId(signinRequestDto.getUserId())
                                        .userEmail(signinRequestDto.getUserEmail())
                                        .userName(signinRequestDto.getUserName())
                                        .userPwd(signinRequestDto.getUserPwd())
                                        .nickname(signinRequestDto.getUserNickName())
                                        .phoneNo(signinRequestDto.getUserPhone())
                                        .usertype("NORMAL")
                                        .regdate(date)
                                        .build();

            signupRepository.save(signinEntity);

            return "SUCCESS";
        }

    }

    // sns 계정 로그인 유저 가입
    @Override
    public String snsSignin(SocialUserResponse socialUserResponse) {

        String checkUserId = signupRepository.searchUser(socialUserResponse.getId());

        if("T".equals(checkUserId)){
            return "ALREADY_USER";
            
        } else {
            Date date = new Date(); // 오늘 날짜

            SigninEntity signinEntity = SigninEntity.builder()
                                        .userId(socialUserResponse.getId())
                                        .userEmail(socialUserResponse.getEmail())
                                        .userName(socialUserResponse.getName())
                                        .userPwd("")
                                        .nickname(socialUserResponse.getName())
                                        .phoneNo(socialUserResponse.getMobile())
                                        .usertype(socialUserResponse.getSnsType())
                                        .regdate(date)
                                        .build();

            signupRepository.save(signinEntity);

            return "SUCCESS";
        }

    }   
        
}
