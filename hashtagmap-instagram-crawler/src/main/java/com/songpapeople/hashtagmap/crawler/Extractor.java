package com.songpapeople.hashtagmap.crawler;

import com.google.gson.JsonElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {
    private static final int TARGET_INDEX = 2;
    public static final String PARENT_KEY = "node";

    public static String extractByRegex(String body, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);
        if (matcher.find()) {
            return matcher.group(TARGET_INDEX);
        }
        throw new IllegalArgumentException("body 내에서 원하는 내용을 찾지 못했습니다.");
    }

    public static String extractByKey(JsonElement edge, String key) {
        return edge.getAsJsonObject()
                .get(PARENT_KEY).getAsJsonObject()
                .get(key).getAsString();
    }
}
