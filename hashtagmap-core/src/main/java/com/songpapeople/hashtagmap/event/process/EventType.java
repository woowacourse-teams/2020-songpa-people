package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.model.Event;
import com.songpapeople.hashtagmap.event.model.KakaoEvent;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum EventType {
    KAKAO(KakaoEvent.class, 1, 1, 1);

    private final Class<? extends Event> type;
    private final int corePoolSize;
    private final int maxPoolSize;
    private final int queueCapacity;

    EventType(final Class<? extends Event> type, final int corePoolSize, final int maxPoolSize, final int queueCapacity) {
        this.type = type;
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queueCapacity = queueCapacity;
    }

    public static List<Class<? extends Event>> getTypes() {
        return Arrays.stream(values())
                .map(eventType -> eventType.type)
                .collect(Collectors.toList());
    }

    public static EventType findBy(final Class<? extends Event> eventClass) {
        return Arrays.stream(values())
                .filter(eventType -> eventType.type == eventClass)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("처리할 수 없는 이벤트 타입 : " + eventClass.getSimpleName()));
    }

    public String getTypeName() {
        return this.type.getSimpleName();
    }
}
