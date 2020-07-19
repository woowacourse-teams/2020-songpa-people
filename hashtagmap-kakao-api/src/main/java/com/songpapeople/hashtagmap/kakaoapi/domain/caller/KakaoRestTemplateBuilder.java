package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import com.songpapeople.hashtagmap.kakaoapi.domain.exception.KakaoExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.time.Duration;

public class KakaoRestTemplateBuilder {
    private static final String BASE_URL = "https://dapi.kakao.com";

    public static RestTemplateBuilder get(KakaoProperties kakaoProperties) {
        return new RestTemplateBuilder()
                .rootUri(BASE_URL)
                .errorHandler(new KakaoExceptionHandler())
                .defaultHeader("Authorization", "KakaoAK " + kakaoProperties.getKey())
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10));
    }
}
