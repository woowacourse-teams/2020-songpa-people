package com.songpapeople.hashtagmap.web.config;

import com.songpapeople.hashtagmap.web.property.LogProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = LogProperties.class)
public class LogPropertiesConfiguration {
}
