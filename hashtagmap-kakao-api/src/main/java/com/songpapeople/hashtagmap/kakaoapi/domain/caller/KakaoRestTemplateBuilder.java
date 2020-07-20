package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import com.songpapeople.hashtagmap.kakaoapi.domain.exception.KakaoExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.time.Duration;

public class KakaoRestTemplateBuilder {
    public static RestTemplateBuilder get(KakaoSecurityProperties kakaoSecurityProperties,
                                          KakaoProperties kakaoProperties) {
        return new RestTemplateBuilder()
                .rootUri(kakaoProperties.getBaseUrl())
                .errorHandler(new KakaoExceptionHandler())
                .defaultHeader("Authorization", "KakaoAK " + kakaoSecurityProperties.getKey())
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10));
    }
}
