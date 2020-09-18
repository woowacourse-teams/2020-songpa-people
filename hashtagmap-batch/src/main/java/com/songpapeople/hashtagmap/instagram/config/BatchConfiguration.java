package com.songpapeople.hashtagmap.instagram.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "batch")
@Configuration
public class BatchConfiguration {
    private int chunk;
}
