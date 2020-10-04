package com.songpapeople.hashtagmap.web.model;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import com.songpapeople.hashtagmap.web.util.HttpUtils;
import com.songpapeople.hashtagmap.web.util.MDCUtils;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class ErrorLog {
    private final String phase;
    private final String loggerName;
    private final String path;
    private final String message;
    private final LocalDateTime errorDatetime = LocalDateTime.now();
    private final String alertYn = "N";
    private final String headers;
    private final String requestBody;
    private final String userInfo;
    private final String agentDetail;
    private final String requestMethod;
    private final String hostName = HttpUtils.getHostName();
    private String trace = "";

    public ErrorLog(final String profiles, final ILoggingEvent loggingEvent) {
        this.phase = profiles;
        this.loggerName = (loggingEvent.getLoggerName());
        this.message = (loggingEvent.getFormattedMessage());
        this.path = (MDCUtils.get(MDCUtils.REQUEST_URI_MDC));
        this.requestMethod = (MDCUtils.get(MDCUtils.REQUEST_METHOD_MDC));
        this.headers = (MDCUtils.get(MDCUtils.HEADERS_MDC));
        this.requestBody = (MDCUtils.get(MDCUtils.REQUEST_BODY_MCD));
        this.userInfo = (MDCUtils.get(MDCUtils.USER_INFO_MDC));
        this.agentDetail = (MDCUtils.get(MDCUtils.AGENT_DETAIL_MDC));
        extractStackTrace(loggingEvent).ifPresent(this::setTrace);
    }

    private Optional<String> extractStackTrace(ILoggingEvent loggingEvent) {
        IThrowableProxy throwableProxy = loggingEvent.getThrowableProxy();
        if (ObjectUtils.isEmpty(throwableProxy)) {
            return Optional.empty();
        }

        StackTraceElementProxy[] stackTraceElements = throwableProxy.getStackTraceElementProxyArray();
        if (ObjectUtils.isEmpty(stackTraceElements)) {
            return Optional.empty();
        }

        StringBuilder sb = new StringBuilder();
        for (StackTraceElementProxy element : stackTraceElements) {
            sb.append(element.toString());
            sb.append("\n");
        }

        return Optional.of(sb.toString());
    }

    public String getRequestURI() {
        return "[" + getRequestMethod() + "] " + getPath();
    }

    public String getTime() {
        return this.getErrorDatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private void setTrace(final String trace) {
        this.trace = trace;
    }
}