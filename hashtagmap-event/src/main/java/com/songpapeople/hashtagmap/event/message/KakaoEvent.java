package com.songpapeople.hashtagmap.event.message;

import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class KakaoEvent extends Event {
    private final Category category;
    private final Zone zone;

    private Long eventHistoryId;

    public KakaoEvent(final Consumer<Event> eventConsumer, final Category category, final Zone zone) {
        super(eventConsumer);
        this.category = category;
        this.zone = zone;
    }

    public String getCategoryGroupCode() {
        return this.category.getCategoryGroupCode();
    }

    public void placeId(final Long eventHistoryId) {
        this.eventHistoryId = eventHistoryId;
    }

}
