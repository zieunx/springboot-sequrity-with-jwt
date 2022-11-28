package com.study.jwt.config.security;

import com.study.jwt.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomFilter> customFilter() {
        FilterRegistrationBean<CustomFilter> bean = new FilterRegistrationBean<>(new CustomFilter());

        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}
