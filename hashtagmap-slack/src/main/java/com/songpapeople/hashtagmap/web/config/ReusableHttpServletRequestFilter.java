package com.songpapeople.hashtagmap.web.config;

import com.songpapeople.hashtagmap.web.ReusableHttpServletRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ReusableHttpServletRequestFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        ReusableHttpServletRequest reusableHttpServletRequest = new ReusableHttpServletRequest((HttpServletRequest) request);
        chain.doFilter(reusableHttpServletRequest, response);
    }

    public void destroy() {
    }
}