package com.songpapeople.hashtagmap.dto;

import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.model.TagLevels;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TagLevelResponse {
    private final Long level;
    private final Long minHashtagCount;
    private final Long maxHashtagCount;

    private TagLevelResponse(Long level, Long minHashtagCount, Long maxHashtagCount) {
        this.level = level;
        this.minHashtagCount = minHashtagCount;
        this.maxHashtagCount = maxHashtagCount;
    }

    public static List<TagLevelResponse> of(TagLevels tagLevels) {
        List<TagLevelResponse> tagLevelResponses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TagLevel tagLevel = tagLevels.get(i);
            TagLevelResponse tagLevelResponse = TagLevelResponse.of(tagLevel);
            tagLevelResponses.add(tagLevelResponse);
        }
        return tagLevelResponses;
    }

    private static TagLevelResponse of(TagLevel tagLevel) {
        return new TagLevelResponse(tagLevel.getId(), tagLevel.getMinHashtagCount(), tagLevel.getMaxHashtagCount());
    }
}
