package com.songpapeople.hashtagmap.web.property;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "log")
public class LogProperties {
    private final Level level;
    private final Slack slack;

    public boolean canLog(ILoggingEvent loggingEvent) {
        return loggingEvent.getLevel().equals(this.level);
    }

    public boolean isSlackEnabled() {
        return this.slack.isEnabled();
    }

    public String getSlackWebHookUrl() {
        return this.slack.getWebHookUrl();
    }

    public String getSlackChannel() {
        return this.slack.getChannel();
    }

    @Getter
    @RequiredArgsConstructor
    public static class Slack {
        private final boolean enabled;
        private final String webHookUrl;
        private final String channel;
    }

}