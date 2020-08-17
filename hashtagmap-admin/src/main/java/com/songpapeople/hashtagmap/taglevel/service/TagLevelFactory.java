package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TagLevelFactory {

    public List<TagLevel> update(List<Long> hashtagCounts, List<TagLevel> tagLevels) {
        int tagLevelSize = tagLevels.size();
        int tagLevelRange = hashtagCounts.size() / tagLevelSize;

        validateHashtagCount(hashtagCounts.size(), tagLevelSize);

        for (int i = 0; i < tagLevelSize; i++) {
            Long minHashtagCount = getMinHashtagCount(hashtagCounts, tagLevelRange, i);
            Long maxHashtagCount = getMaxHashtagCount(hashtagCounts, tagLevelRange, i, tagLevels.size());

            tagLevels.get(i).updateMinHashtagCount(minHashtagCount);
            tagLevels.get(i).updateMaxHashtagCount(maxHashtagCount);
        }
        return tagLevels;
    }

    private Long getMinHashtagCount(List<Long> hashtagCounts, int tagLevelRange, int tagLevelIndex) {
        int minHashtagIndex = tagLevelRange * tagLevelIndex;
        return hashtagCounts.get(minHashtagIndex);
    }

    private Long getMaxHashtagCount(List<Long> hashtagCounts, int tagLevelRange,
                                    int tagLevelIndex, int tagLevelSize) {
        int maxHashtagIndex = tagLevelRange * (tagLevelIndex + 1) - 1;
        if (tagLevelIndex == tagLevelSize - 1) {
            return hashtagCounts.get(hashtagCounts.size() - 1);
        }
        return hashtagCounts.get(maxHashtagIndex);
    }

    private void validateHashtagCount(int hashtagCountSize, int tagLevelSize) {
        if (hashtagCountSize < tagLevelSize) {
            String detailErrorMessage = String.format("인스타그램 포스팅 개수(%s)가 태그레벨 개수(%s)보다 적어 갱신할 수 없습니다.", hashtagCountSize, tagLevelSize);
            log.error("AdminException:" + detailErrorMessage);
            throw new AdminException(AdminExceptionStatus.INVALID_TAG_LEVEL_UPDATE, detailErrorMessage);
        }
    }
}
