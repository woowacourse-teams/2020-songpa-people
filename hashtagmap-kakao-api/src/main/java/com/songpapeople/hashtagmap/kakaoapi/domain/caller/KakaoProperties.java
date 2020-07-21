package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "kakao")
@Component
public class KakaoProperties {
    private String baseUrl;
    private String categoryUrl;
    private String categoryGroupCode;
    private int maxDocumentCount;
    private int maxPageableCount;
}
