package com.songpapeople.hashtagmap.kakaoapi.config;

import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoApiCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoProperties;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoRestTemplateBuilder;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.MajorKakaoApiCaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MajorKakaoConfiguration {
    private final KakaoProperties kakaoProperties;

    public MajorKakaoConfiguration(KakaoProperties kakaoProperties) {
        this.kakaoProperties = kakaoProperties;
    }

    @Bean
    public KakaoApiCaller majorKakaoApiCaller() {
        RestTemplate restTemplate = KakaoRestTemplateBuilder.get(kakaoProperties).build();
        return new MajorKakaoApiCaller(restTemplate);
    }
}
