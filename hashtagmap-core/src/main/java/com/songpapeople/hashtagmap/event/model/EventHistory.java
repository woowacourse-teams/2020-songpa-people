package com.songpapeople.hashtagmap.event.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@AttributeOverride(name = "id", column = @Column(name = "EVENT_ID"))
public abstract class EventHistory extends BaseEntity {
    @Enumerated(EnumType.STRING)
    protected EventStatus eventStatus;

    public EventHistory(final EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void fail() {
        this.eventStatus = EventStatus.FAIL;
    }

    public void success() {
        this.eventStatus = EventStatus.SUCCESS;
    }
}
