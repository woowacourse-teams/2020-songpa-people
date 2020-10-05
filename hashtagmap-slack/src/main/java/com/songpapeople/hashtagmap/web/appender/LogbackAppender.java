package com.songpapeople.hashtagmap.web.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.songpapeople.hashtagmap.web.model.ErrorLog;
import com.songpapeople.hashtagmap.web.model.SlackApiWrapper;
import com.songpapeople.hashtagmap.web.property.LogProperties;

import java.util.Optional;

public class LogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private final LogProperties logProperties;
    private final String profiles;

    public LogbackAppender(LogProperties logProperties, String profiles) {
        this.logProperties = logProperties;
        this.profiles = profiles;
    }

    @Override
    public void doAppend(ILoggingEvent eventObject) {
        super.doAppend(eventObject);
    }

    @Override
    protected void append(ILoggingEvent loggingEvent) {
        if (logProperties.canLog(loggingEvent) && logProperties.isSlackEnabled()) {
            getErrorLog(loggingEvent).ifPresent(this::toSlack);
        }
    }

    private void toSlack(ErrorLog errorLog) {
        SlackApiWrapper slackApiWrapper = new SlackApiWrapper(logProperties);
        slackApiWrapper.call(errorLog);
    }

    public Optional<ErrorLog> getErrorLog(ILoggingEvent loggingEvent) {
        if (logProperties.canLog(loggingEvent)) {
            ErrorLog errorLog = new ErrorLog(this.profiles, loggingEvent);
            return Optional.of(errorLog);
        }
        return Optional.empty();
    }

}
