package com.songpapeople.hashtagmap.taglevel.model;

import com.songpapeople.hashtagmap.exception.CoreException;
import com.songpapeople.hashtagmap.exception.CoreExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.HashtagCounts;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TagLevels {
    private static final long FIRST_TAG_LEVEL_ID = 1L;

    private final List<TagLevel> tagLevels;

    public TagLevels(List<TagLevel> tagLevels) {
        this.tagLevels = tagLevels;
    }

    public TagLevels update(HashtagCounts hashtagCounts) {
        validateSize(hashtagCounts);

        for (int index = 0; index < tagLevels.size(); index++) {
            Long minHashtagCount = getMinHashtagCount(hashtagCounts, index);
            Long maxHashtagCount = getMaxHashtagCount(hashtagCounts, index);
            tagLevels.get(index)
                    .update(minHashtagCount, maxHashtagCount);
        }
        return new TagLevels(tagLevels);
    }

    private void validateSize(HashtagCounts hashtagCounts) {
        if (tagLevels.size() > hashtagCounts.getSize()) {
            String detailMessage = String.format("해시태그 개수(%s)가 태그레벨(%s) 개수보다 적어 갱신할 수 없습니다",
                    hashtagCounts.getSize(),
                    tagLevels.size());
            log.info("CoreException:" + detailMessage);
            throw new CoreException(CoreExceptionStatus.INVALID_TAG_LEVEL, detailMessage);
        }
    }

    public Long findIdByHashtagCount(Long hashtagCount) {
        for (TagLevel tagLevel : tagLevels) {
            if (tagLevel.isContains(hashtagCount)) {
                return tagLevel.getId();
            }
        }
        log.info(String.format("CoreException:태그레벨에 맞지 않는 hashtagCount(%s)가 존재합니다.", hashtagCount));
        return FIRST_TAG_LEVEL_ID;
    }

    private Long getMinHashtagCount(HashtagCounts hashtagCounts, int tagLevelIndex) {
        int minHashtagIndex = getTagLevelRange(hashtagCounts) * tagLevelIndex;
        return hashtagCounts.get(minHashtagIndex);
    }

    private Long getMaxHashtagCount(HashtagCounts hashtagCounts, int tagLevelIndex) {
        int maxHashtagIndex = getTagLevelRange(hashtagCounts) * (tagLevelIndex + 1) - 1;
        if (isLastIndex(tagLevelIndex)) {
            maxHashtagIndex = hashtagCounts.getLastIndex();
        }
        return hashtagCounts.get(maxHashtagIndex);
    }

    private int getTagLevelRange(HashtagCounts hashtagCounts) {
        return hashtagCounts.getSize() / tagLevels.size();
    }

    private boolean isLastIndex(int index) {
        return (tagLevels.size() - 1) == index;
    }

    public TagLevel get(int index) {
        return tagLevels.get(index);
    }

    public int getTagLevelsSize() {
        return this.tagLevels.size();
    }
}
