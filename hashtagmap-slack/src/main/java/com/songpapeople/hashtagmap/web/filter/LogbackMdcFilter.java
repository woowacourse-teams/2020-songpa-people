package com.songpapeople.hashtagmap.web.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.songpapeople.hashtagmap.web.util.MDCUtil;
import com.songpapeople.hashtagmap.web.util.RequestWrapper;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class LogbackMdcFilter implements Filter {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper requestWrapper = RequestWrapper.of(request);

        MDCUtil.setJsonValue(MDCUtil.HEADER_MAP_MDC, requestWrapper.getHeaderMap());
        MDCUtil.setJsonValue(MDCUtil.PARAMETER_MAP_MDC, requestWrapper.getParameterMap());
        MDCUtil.set(MDCUtil.REQUEST_URI_MDC, requestWrapper.getRequestUri());

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
    }
}