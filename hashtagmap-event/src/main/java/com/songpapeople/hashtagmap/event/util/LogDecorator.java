package com.songpapeople.hashtagmap.event.util;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class LogDecorator implements TaskDecorator {
    private final Map<String, String> contextMap;

    public LogDecorator(final Map<String, String> contextMap) {
        this.contextMap = contextMap;
    }

    @Override
    public Runnable decorate(final Runnable runnable) {
        return () -> {
            try {
                MDC.setContextMap(contextMap);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
