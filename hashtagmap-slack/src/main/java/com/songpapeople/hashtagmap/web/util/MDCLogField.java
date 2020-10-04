package com.songpapeople.hashtagmap.web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiConsumer;
import java.util.function.Function;

public enum MDCLogField {
    HEADERS_MDC("HEADERS_MDC", HttpReadUtils::getHeaders, MDCLogField::putJsonValue),
    USER_ADDRESS_MDC("USER_ADDRESS_MDC", HttpReadUtils::getUserAddress, (key, value) -> MDCLogField.put(key, (String) value)),
    REQUEST_URI_MDC("REQUEST_URI_MDC", HttpReadUtils::getRequestURLWithQueryString, (key, value) -> MDCLogField.put(key, (String) value)),
    REQUEST_METHOD_MDC("REQUEST_METHOD_MDC", HttpServletRequest::getMethod, (key, value) -> MDCLogField.put(key, (String) value)),
    AGENT_DETAIL_MDC("AGENT_DETAIL_MDC", HttpReadUtils::getUserAgent, (key, value) -> MDCLogField.put(key, (String) value)),
    REQUEST_BODY_MDC("REQUEST_BODY_MDC", HttpReadUtils::getHttpBody, (key, value) -> MDCLogField.put(key, (String) value)),
    USER_REFERER_MDC("USER_REFERER_MDC", HttpReadUtils::getReferer, (key, value) -> MDCLogField.put(key, (String) value));

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String key;
    private final Function<HttpServletRequest, Object> requestGetter;
    private final BiConsumer<String, Object> mdcSetter;

    MDCLogField(final String key, final Function<HttpServletRequest, Object> requestGetter, final BiConsumer<String, Object> mdcSetter) {
        this.key = key;
        this.requestGetter = requestGetter;
        this.mdcSetter = mdcSetter;
    }

    public static void putMDCLogFields(HttpServletRequest request) {
        for (MDCLogField field : values()) {
            Object value = field.requestGetter.apply(request);
            field.mdcSetter.accept(field.key, value);
        }
    }

    public static void put(String key, String value) {
        MDCAdapter mdc = MDC.getMDCAdapter();
        mdc.put(key, value);
    }

    public static void putJsonValue(String key, Object value) {
        MDCAdapter mdc = MDC.getMDCAdapter();
        mdc.put(key, gson.toJson(value));
    }

    public String get() {
        MDCAdapter mdc = MDC.getMDCAdapter();
        return mdc.get(this.key);
    }
}
