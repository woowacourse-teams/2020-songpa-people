package com.songpapeople.hashtagmap.taglevel.domain;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "TAG_LEVEL_ID"))
public class TagLevel extends BaseEntity {
    Long minHashtagCount;
    Long maxHashtagCount;
    // todo DB에 Table 생성

    public TagLevel(Long id) {
        this.id = id;
    }

    public TagLevel(Long id, Long minHashtagCount, Long maxHashtagCount) {
        this.id = id;
        this.minHashtagCount = minHashtagCount;
        this.maxHashtagCount = maxHashtagCount;
    }

    public void updateMinHashtagCount(Long minHashtagCount) {
        this.minHashtagCount = minHashtagCount;
    }

    public void updateMaxHashtagCount(Long maxHashtagCount) {
        this.maxHashtagCount = maxHashtagCount;
    }

    public boolean contains(Long hashtagCount) {
        return hashtagCount >= minHashtagCount && hashtagCount <= maxHashtagCount;
    }
}

