package com.songpapeople.hashtagmap.taglevel.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import com.songpapeople.hashtagmap.exception.CoreException;
import com.songpapeople.hashtagmap.exception.CoreExceptionStatus;
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
        validateHashtagCount(minHashtagCount, maxHashtagCount);
        this.id = id;
        this.minHashtagCount = minHashtagCount;
        this.maxHashtagCount = maxHashtagCount;
    }

    public void update(Long minHashtagCount, Long maxHashtagCount) {
        validateHashtagCount(minHashtagCount, maxHashtagCount);
        this.minHashtagCount = minHashtagCount;
        this.maxHashtagCount = maxHashtagCount;
    }

    private void validateHashtagCount(Long minHashtagCount, Long maxHashtagCount) {
        if (minHashtagCount > maxHashtagCount) {
            String detailMessgae = String.format("최소 해시태그 개수(%s)는 최대 해시태그 개수(%s)보다 많을 수 없습니다.",
                    minHashtagCount, maxHashtagCount);
            throw new CoreException(CoreExceptionStatus.INVALID_TAG_LEVEL, detailMessgae);
        }
    }

    public boolean isContains(Long hashtagCount) {
        if (Objects.isNull(minHashtagCount) || Objects.isNull(maxHashtagCount)) {
            return false;
        }
        return hashtagCount >= minHashtagCount && hashtagCount <= maxHashtagCount;
    }
}

