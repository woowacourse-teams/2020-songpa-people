package com.songpapeople.hashtagmap.web.filter;

import com.songpapeople.hashtagmap.web.util.HttpReadUtils;
import com.songpapeople.hashtagmap.web.util.MDCUtils;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogbackMdcFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        MDCUtils.putJsonValue(MDCUtils.HEADERS_MDC, HttpReadUtils.getHeaders(httpServletRequest));
        MDCUtils.put(MDCUtils.REQUEST_URI_MDC, HttpReadUtils.getRequestURLWithQueryString(httpServletRequest));
        MDCUtils.put(MDCUtils.REQUEST_METHOD_MDC, httpServletRequest.getMethod());
        HttpReadUtils.getHttpBody(httpServletRequest, "UTF-8")
                .ifPresent(body -> MDCUtils.put(MDCUtils.REQUEST_BODY_MCD, body));
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