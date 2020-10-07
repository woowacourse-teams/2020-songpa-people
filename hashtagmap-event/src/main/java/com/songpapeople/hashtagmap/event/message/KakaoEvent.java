package com.songpapeople.hashtagmap.event.message;

import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class KakaoEvent implements Event {
    protected final Consumer<KakaoEvent> eventConsumer;
    protected final Consumer<KakaoEvent> failEventConsumer;
    private final Category category;
    private final Zone zone;

    private Long eventHistoryId;

    public KakaoEvent(final Consumer<KakaoEvent> eventConsumer, final Consumer<KakaoEvent> failEventConsumer, final Category category, final Zone zone) {
        this.eventConsumer = eventConsumer;
        this.failEventConsumer = failEventConsumer;
        this.category = category;
        this.zone = zone;
    }

    public String getCategoryGroupCode() {
        return this.category.getCategoryGroupCode();
    }

    public void placeId(final Long eventHistoryId) {
        this.eventHistoryId = eventHistoryId;
    }

    @Override
    public void doService() {
        this.eventConsumer.accept(this);
    }

    @Override
    public void doFailService() {
        this.failEventConsumer.accept(this);
    }
}
