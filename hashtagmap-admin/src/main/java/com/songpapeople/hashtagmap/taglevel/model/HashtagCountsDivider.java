package com.songpapeople.hashtagmap.taglevel.model;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.HashtagCounts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashtagCountsDivider {
    private final TagLevels tagLevels;
    private final HashtagCounts hashtagCounts;
    private final int tagLevelRange;

    public HashtagCountsDivider(TagLevels tagLevels, HashtagCounts hashtagCounts) {
        this.tagLevels = tagLevels;
        this.hashtagCounts = hashtagCounts;

        validateSize(tagLevels, hashtagCounts);
        this.tagLevelRange = getTagLevelRange();
    }

    private void validateSize(TagLevels tagLevels, HashtagCounts hashtagCounts) {
        if (tagLevels.getSize() > hashtagCounts.getSize()) {
            String detailMessage = String.format("해시태그 개수(%s)가 태그레벨(%s) 개수보다 적어 갱신할 수 없습니다",
                    hashtagCounts.getSize(),
                    tagLevels.getSize());
            log.info("AdminException:" + detailMessage);
            throw new AdminException(AdminExceptionStatus.INVALID_TAG_LEVEL_UPDATE, detailMessage);
        }
    }

    public Long getMinHashtagCountByTagLevel(int tagLevelIndex) {
        int minHashtagIndex = tagLevelRange * tagLevelIndex;
        return hashtagCounts.get(minHashtagIndex);
    }

    public Long getMaxHashtagCountByTagLevel(int tagLevelIndex) {
        int maxHashtagIndex = tagLevelRange * (tagLevelIndex + 1) - 1;
        if (tagLevels.isLastIndex(tagLevelIndex)) {
            maxHashtagIndex = hashtagCounts.getLastIndex();
        }
        return hashtagCounts.get(maxHashtagIndex);
    }

    private int getTagLevelRange() {
        return hashtagCounts.getSize() / tagLevels.getSize();
    }
}
