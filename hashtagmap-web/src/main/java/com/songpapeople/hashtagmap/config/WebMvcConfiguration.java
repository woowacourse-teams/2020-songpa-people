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
        for (StaticResourcePath path : StaticResourcePath.values()) {
            ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler(path.getPath());
            resourceHandlerRegistration.addResourceLocations("classpath:/static/" + path.getDirectory() + "/")
                    .setCacheControl(CacheControl.maxAge(60L * 60L * 24L * 365L, TimeUnit.SECONDS).cachePublic());
        }
    }

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean = new FilterRegistrationBean<>(
                new ShallowEtagHeaderFilter());
        filterRegistrationBean.addUrlPatterns("/js/*", "/css/*", "/img/*");
        return filterRegistrationBean;
    }
}
