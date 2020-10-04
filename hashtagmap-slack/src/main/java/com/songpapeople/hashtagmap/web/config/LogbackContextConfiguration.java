package com.songpapeople.hashtagmap.web.config;

import ch.qos.logback.classic.LoggerContext;
import com.songpapeople.hashtagmap.web.appender.LogbackAppender;
import com.songpapeople.hashtagmap.web.property.LogProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class LogbackContextConfiguration implements InitializingBean {
    private final LogProperties logProperties;
    private final Environment env;

    @Override
    public void afterPropertiesSet() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        LogbackAppender logBackAppender = new LogbackAppender(logProperties, String.join(",", env.getActiveProfiles()));

        logBackAppender.setContext(loggerContext);
        logBackAppender.setName("logbackAppender");
        logBackAppender.start();
        loggerContext.getLogger("ROOT").addAppender(logBackAppender);
    }
}