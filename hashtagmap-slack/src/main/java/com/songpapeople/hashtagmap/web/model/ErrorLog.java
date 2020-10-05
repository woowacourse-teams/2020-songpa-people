package com.songpapeople.hashtagmap.web.model;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import com.songpapeople.hashtagmap.web.util.HttpUtils;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Getter
public class ErrorLog {
    private final String phase;
    private final String loggerName;
    private final String message;

    // MDC 영역
    private final String path = MDCLogField.REQUEST_URI_MDC.get();
    private final String requestMethod = MDCLogField.REQUEST_METHOD_MDC.get();
    private final String headers = MDCLogField.HEADERS_MDC.get();
    private final String requestBody = MDCLogField.REQUEST_BODY_MDC.get();
    private final String userAddress = MDCLogField.USER_ADDRESS_MDC.get();
    private final String agentDetail = MDCLogField.AGENT_DETAIL_MDC.get();
    private final String referer = MDCLogField.USER_REFERER_MDC.get();
    private final String hostName = HttpUtils.getHostName();

    private String trace = "";
    private final LocalDateTime errorDatetime = LocalDateTime.now();

    public ErrorLog(final String profiles, final ILoggingEvent loggingEvent) {
        this.phase = profiles;
        this.loggerName = loggingEvent.getLoggerName();
        this.message = loggingEvent.getFormattedMessage();

        extractStackTrace(loggingEvent).ifPresent(this::setTrace);
    }

    private Optional<String> extractStackTrace(ILoggingEvent loggingEvent) {
        IThrowableProxy throwableProxy = loggingEvent.getThrowableProxy();
        if (Objects.isNull(throwableProxy)) {
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