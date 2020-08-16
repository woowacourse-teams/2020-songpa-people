package com.songpapeople.hashtagmap.taglevel.domain;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "TAG_LEVEL_ID"))
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "LEVEL", name = "UK_LEVEL"))
public class TagLevel extends BaseEntity {
    Long level;

    Long minHashtagCount;
    Long maxHashtagCount;
    // todo DB에 Table 생성

    public TagLevel(Long level) {
        this.level = level;
    }

    public void update(List<Long> hashtagCount) {
        this.minHashtagCount = hashtagCount.get(0);
        this.maxHashtagCount = hashtagCount.get(1);
    }
}

