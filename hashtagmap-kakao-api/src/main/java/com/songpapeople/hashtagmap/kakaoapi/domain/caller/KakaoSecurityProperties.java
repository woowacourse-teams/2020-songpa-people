package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
public class KakaoSecurityProperties {
    private String key;
}
