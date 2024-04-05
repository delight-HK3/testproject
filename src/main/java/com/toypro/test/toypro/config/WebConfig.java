package com.toypro.test.toypro.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.toypro.test.toypro.filter.LoginCheckFilter;

import jakarta.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE")
                .maxAge(3600);
    }

    // 필터 생성하기
    @SuppressWarnings("rawtypes")
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
        filterRegistrationBean.setFilter(new LoginCheckFilter()); //내가 구현한 필터 넣기
        filterRegistrationBean.setOrder(2); //필터 체인할 때 가장 먼저 실행
        filterRegistrationBean.addUrlPatterns("/*"); //모든 요청 url에 대해 실행
        
        return filterRegistrationBean;
    }
}
