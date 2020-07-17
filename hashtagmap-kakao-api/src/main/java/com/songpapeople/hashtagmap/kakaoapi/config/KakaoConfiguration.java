package com.songpapeople.hashtagmap.kakaoapi.config;

import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoApiCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoProperties;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoRestTemplateApiCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoRestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KakaoConfiguration {
    private final KakaoProperties kakaoProperties;

    public KakaoConfiguration(KakaoProperties kakaoProperties) {
        this.kakaoProperties = kakaoProperties;
    }

    @Bean
    public KakaoApiCaller kakaoRestTemplateApiCaller() {
        RestTemplate restTemplate = KakaoRestTemplateBuilder.get(kakaoProperties).build();
        return new KakaoRestTemplateApiCaller(restTemplate);
    }
}
