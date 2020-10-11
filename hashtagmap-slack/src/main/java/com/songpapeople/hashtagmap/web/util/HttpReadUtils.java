package com.songpapeople.hashtagmap.web.util;

import com.songpapeople.hashtagmap.web.web.MultiReadHttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class HttpReadUtils {
    public static final Set<String> MULTI_READ_HTTP_METHODS = new HashSet<>();

    static {
        MULTI_READ_HTTP_METHODS.add("POST");
        MULTI_READ_HTTP_METHODS.add("PUT");
        MULTI_READ_HTTP_METHODS.add("PATCH");
        MULTI_READ_HTTP_METHODS.add("DELETE");
    }

    public static String getHttpBody(HttpServletRequest request) {
        return getHttpBody(request, "UTF-8");
    }

    public static String getHttpBody(HttpServletRequest request, String encoding) {
        if (!isReadableHttpBody(request.getMethod()) ||
                !(request instanceof MultiReadHttpServletRequest)) {
            return null;
        }

        if (isFormUrlencoded(request)) {
            return readParameters(request);
        }

        return readBody(request, encoding);
    }

    private static boolean isReadableHttpBody(String method) {
        return MULTI_READ_HTTP_METHODS.contains(method);
    }

    private static boolean isFormUrlencoded(HttpServletRequest request) {
        return Objects.nonNull(request.getContentType()) &&
                MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(request.getContentType());
    }

    private static String readParameters(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder("[");

        Iterator<Map.Entry<String, String[]>> iterator = request.getParameterMap()
                .entrySet()
                .iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();

            String[] value = entry.getValue();
            for (int i = 0; i < value.length; i++) {
                sb.append(entry.getKey()).append("=").append(value[i]);
                if (i < value.length - 1) {
                    sb.append("&");
                }
            }

            if (iterator.hasNext()) {
                sb.append("&");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String getRequestURLWithQueryString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(128);

        sb.append(request.getRequestURL());
        if (null != request.getQueryString()) {
            sb.append("?");
            sb.append(request.getQueryString());
        }
        return sb.toString();
    }

    private static String readBody(HttpServletRequest request, String encoding) {
        try {
            if (Objects.isNull(request.getInputStream())) {
                return null;
            }
            return StreamUtils.copyToString(request.getInputStream(), Charset.forName(encoding));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        return headerMap;
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static String getUserAddress(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("X-FORWARDED-FOR"))
                .orElseGet(request::getRemoteAddr);
    }

    public static String getReferer(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("referer"))
                .orElse("직접 접근");
    }
}