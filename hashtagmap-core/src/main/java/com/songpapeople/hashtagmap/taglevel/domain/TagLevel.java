package com.songpapeople.hashtagmap.taglevel.domain;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "TAG_LEVEL_ID"))
public class TagLevel extends BaseEntity {
    private Long minHashtagCount;
    private Long maxHashtagCount;

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

    public boolean isContains(Long hashtagCount) {
        if (Objects.isNull(minHashtagCount) || Objects.isNull(maxHashtagCount)) {
            return false;
        }
        return hashtagCount >= minHashtagCount && hashtagCount <= maxHashtagCount;
    }
}

