package com.songpapeople.hashtagmap.kakao.schedule.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import com.songpapeople.hashtagmap.config.vo.Flag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "SCHEDULE_ID"))
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_SCEHDULE_TARGET", columnNames = "target"))
public class Schedule extends BaseEntity {

    private String target;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Flag flag;

    public Schedule(final String target, final String name, final Flag flag) {
        this.target = target;
        this.name = name;
        this.flag = flag;
    }

    public void toggle() {
        flag = flag.toggle();
    }

    public boolean isActive() {
        return this.flag.isYes();
    }
}
