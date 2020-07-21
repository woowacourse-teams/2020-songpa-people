package com.songpapeople.hashtagmap.kakaoapi.config;

import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoProperties;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoRestTemplateApiCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoRestTemplateBuilder;
import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoSecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KakaoConfiguration {
    private final KakaoSecurityProperties kakaoSecurityProperties;
    private final KakaoProperties kakaoProperties;

    public KakaoConfiguration(KakaoSecurityProperties kakaoSecurityProperties, KakaoProperties kakaoProperties) {
        this.kakaoSecurityProperties = kakaoSecurityProperties;
        this.kakaoProperties = kakaoProperties;
    }

    @Bean
    public KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller() {
        RestTemplate restTemplate = KakaoRestTemplateBuilder.get(kakaoSecurityProperties, kakaoProperties)
                .build();
        return new KakaoRestTemplateApiCaller(restTemplate, kakaoProperties);
    }
}
