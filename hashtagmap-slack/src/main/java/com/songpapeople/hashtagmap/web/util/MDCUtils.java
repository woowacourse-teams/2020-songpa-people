package com.songpapeople.hashtagmap.web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;

public class MDCUtils {
    public static final String HEADERS_MDC = "HEADERS_MDC";
    public static final String USER_INFO_MDC = "USER_INFO_MDC";
    public static final String REQUEST_URI_MDC = "REQUEST_URI_MDC";
    public static final String REQUEST_METHOD_MDC = "REQUEST_METHOD_MDC";
    public static final String AGENT_DETAIL_MDC = "AGENT_DETAIL_MDC";
    public static final String REQUEST_BODY_MCD = "REQUEST_BODY_MDC";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void put(String key, String value) {
        MDCAdapter mdc = MDC.getMDCAdapter();
        mdc.put(key, value);
    }

    public static void putJsonValue(String key, Object value) {
        MDCAdapter mdc = MDC.getMDCAdapter();
        mdc.put(key, gson.toJson(value));
    }

    public static String get(String key) {
        MDCAdapter mdc = MDC.getMDCAdapter();
        return mdc.get(key);
    }
}