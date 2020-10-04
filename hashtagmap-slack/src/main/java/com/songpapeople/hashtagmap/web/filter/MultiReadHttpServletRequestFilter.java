package com.songpapeople.hashtagmap.web.filter;

import com.songpapeople.hashtagmap.web.web.MultiReadHttpServletRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MultiReadHttpServletRequestFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        MultiReadHttpServletRequest multiReadHttpServletRequest = new MultiReadHttpServletRequest((HttpServletRequest) request, MultiReadHttpServletRequest.DEFAULT_ENCODING);
        chain.doFilter(multiReadHttpServletRequest, response);
    }

    public void destroy() {
    }
}