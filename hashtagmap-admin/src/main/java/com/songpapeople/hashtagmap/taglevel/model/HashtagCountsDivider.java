package com.songpapeople.hashtagmap.taglevel.model;

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
        this.tagLevelRange = getTagLevelRange();
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
