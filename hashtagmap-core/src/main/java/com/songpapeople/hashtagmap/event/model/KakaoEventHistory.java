package com.songpapeople.hashtagmap.event.model;

import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.function.Consumer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "KAKAO")
@AttributeOverride(name = "id", column = @Column(name = "EVENT_ID"))
public class KakaoEventHistory extends EventHistory {
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZONE_ID", foreignKey = @ForeignKey(name = "FK_KAKAO_EVENT_ZONE"))
    private Zone zone;

    public KakaoEventHistory(final Category category, final Zone zone) {
        this.category = category;
        this.zone = zone;
    }

    @Override
    public void doEvent(Consumer<EventHistory> eventConsumer) {
        eventConsumer.accept(this);
    }

    public String getCategoryGroupCode() {
        return this.category.getCategoryGroupCode();
    }
}
