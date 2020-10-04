package com.songpapeople.hashtagmap.web.config;

import ch.qos.logback.classic.LoggerContext;
import com.songpapeople.hashtagmap.web.appender.LogBackAppender;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LogContextConfig implements InitializingBean {

    private final LogConfig logConfig;

    @Override
    public void afterPropertiesSet() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        LogBackAppender logBackAppender = new LogBackAppender(logConfig);

        logBackAppender.setContext(loggerContext);
        logBackAppender.setName("logbackAppender");
        logBackAppender.start();
        loggerContext.getLogger("ROOT").addAppender(logBackAppender);
    }
}