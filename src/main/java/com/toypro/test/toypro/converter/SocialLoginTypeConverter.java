package com.toypro.test.toypro.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.toypro.test.toypro.config.Constant.SocialLoginType;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType>{
    
    //  enum 타입의 대문자 값을 소문자로 mapping 가능하도록 하기위하여 생성
    @Override
    public SocialLoginType convert(String s) {
        return SocialLoginType.valueOf(s.toUpperCase());
    }
}
