package com.toypro.test.toypro.converter;

import com.toypro.test.toypro.type.UserType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, UserType> {
    @Override
    public UserType convert(String s) {
        return UserType.valueOf(s.toUpperCase());
    }
}
