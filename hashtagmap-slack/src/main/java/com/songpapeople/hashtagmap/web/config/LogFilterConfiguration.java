package com.songpapeople.hashtagmap.web.config;

import com.songpapeople.hashtagmap.web.filter.LogbackMdcFilter;
import com.songpapeople.hashtagmap.web.filter.MultiReadHttpServletRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class LogFilterConfiguration {
    private static final int MULTI_READ_FILTER_ORDER = 1;
    private static final int MDC_FILTER_ORDER = MULTI_READ_FILTER_ORDER + 10;

    @Bean
    public FilterRegistrationBean<Filter> multiReadableHttpServletRequestFilterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        MultiReadHttpServletRequestFilter multiReadableHttpServletRequestFilter = new MultiReadHttpServletRequestFilter();
        registrationBean.setFilter(multiReadableHttpServletRequestFilter);
        registrationBean.setOrder(MULTI_READ_FILTER_ORDER);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> logbackMdcFilterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        LogbackMdcFilter logbackMdcFilter = new LogbackMdcFilter();
        registrationBean.setFilter(logbackMdcFilter);
        registrationBean.setOrder(MDC_FILTER_ORDER);
        return registrationBean;
    }

}
