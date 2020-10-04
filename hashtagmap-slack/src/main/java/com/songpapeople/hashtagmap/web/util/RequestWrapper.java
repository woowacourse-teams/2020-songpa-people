package com.songpapeople.hashtagmap.web.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestWrapper {

    private final HttpServletRequest request;

    private RequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    public static RequestWrapper of(HttpServletRequest request) {
        return new RequestWrapper(request);
    }

    public static RequestWrapper of(ServletRequest request) {
        return of((HttpServletRequest) request);
    }

    public Map<String, String> getHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        return headerMap;
    }

    public Map<String, String> getParameterMap() {
        Map<String, String> convertedParameterMap = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();

        for (String key : parameterMap.keySet()) {
            String[] values = parameterMap.get(key);
            convertedParameterMap.put(key, String.join(",", values));
        }
        return convertedParameterMap;
    }

    public String getRequestUri() {
        return request.getRequestURI();
    }
}