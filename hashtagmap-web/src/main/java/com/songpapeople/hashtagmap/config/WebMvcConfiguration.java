package com.songpapeople.hashtagmap.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration jsResourceHandler = registry.addResourceHandler("/js/**");
        ResourceHandlerRegistration cssResourceHandler = registry.addResourceHandler("/css/**");
        ResourceHandlerRegistration imgResourceHandler = registry.addResourceHandler("/img/**");
        setCacheController(jsResourceHandler, "js");
        setCacheController(cssResourceHandler, "css");
        setCacheController(imgResourceHandler, "img");
    }

    private void setCacheController(ResourceHandlerRegistration resourceHandlerRegistration, String directory) {
        resourceHandlerRegistration.addResourceLocations("classpath:/static/" + directory + "/")
                .setCacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS).cachePublic());
    }

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean = new FilterRegistrationBean<>(
                new ShallowEtagHeaderFilter());
        filterRegistrationBean.addUrlPatterns("/js/*", "/css/*", "/img/*");
        return filterRegistrationBean;
    }
}
