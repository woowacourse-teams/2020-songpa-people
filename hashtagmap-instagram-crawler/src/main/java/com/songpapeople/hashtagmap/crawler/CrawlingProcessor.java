package com.songpapeople.hashtagmap.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 정제
public class CrawlingProcessor {
    private static final String HASHTAG_COUNT_REGEX = "(\"edge_hashtag_to_media\":\\{\"count\"):([0-9]+)";
    private static final String HASHTAG_POPULAR_INFO_REGEX = "\"edge_hashtag_to_top_posts\":";

    public static String findHashtagCount(String head) {
        Pattern pattern = Pattern.compile(HASHTAG_COUNT_REGEX);
        Matcher matcher = pattern.matcher(head);

        if (matcher.find()) {
            return matcher.group(2);
        }
        throw new IllegalArgumentException("해시태그 카운트를 찾을 수 없습니다.");
    }

    public static String slicePopularInfo(String body) {
        int startIdx = body.lastIndexOf(HASHTAG_POPULAR_INFO_REGEX) + HASHTAG_POPULAR_INFO_REGEX.length();
        String substringBody = body.substring(startIdx);

        int lastIdx = findLastIdx(substringBody);

        return substringBody.substring(0, lastIdx + 1);
    }

    public static int findLastIdx(String substringBody) {
        int bracketCount = 0;
        for (int i = 0; i < substringBody.length(); i++) {
            if (substringBody.charAt(i) == '{') {
                bracketCount++;
                continue;
            }
            if (substringBody.charAt(i) == '}') {
                bracketCount--;
            }
            if (bracketCount == 0) {
                return i;
            }
        }
        return 0;
    }

}
