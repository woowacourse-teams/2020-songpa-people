package com.songpapeople.hashtagmap.web.util;

import ch.qos.logback.core.util.ContextUtil;

public class HttpUtils {
    public static String getHostName() {
        try {
            return ContextUtil.getLocalHostName();
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }
}
