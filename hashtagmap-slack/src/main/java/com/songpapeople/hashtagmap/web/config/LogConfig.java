package com.songpapeople.hashtagmap.web.config;

import ch.qos.logback.classic.Level;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "log")
public class LogConfig {
    private Level level;
    private Slack slack;

    @Getter
    @Setter
    public static class Slack {
        private boolean enabled;
        private String webHookUrl;
        private String channel;
    }

}