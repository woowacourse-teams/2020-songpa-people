package com.songpapeople.hashtagmap.event.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.function.Consumer;

@Getter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Event extends BaseEntity {
    @Enumerated(EnumType.STRING)
    protected EventStatus eventStatus;

    public abstract void doEvent(Consumer<Event> eventConsumer);
}
