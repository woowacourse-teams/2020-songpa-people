package com.songpapeople.hashtagmap.web.config;

import com.songpapeople.hashtagmap.web.filter.LogbackMdcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class LogFilterConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> multiReadableHttpServletRequestFilterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        ReusableHttpServletRequestFilter multiReadableHttpServletRequestFilter = new ReusableHttpServletRequestFilter();
        registrationBean.setFilter(multiReadableHttpServletRequestFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> logbackMdcFilterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        LogbackMdcFilter logbackMdcFilter = new LogbackMdcFilter();
        registrationBean.setFilter(logbackMdcFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
