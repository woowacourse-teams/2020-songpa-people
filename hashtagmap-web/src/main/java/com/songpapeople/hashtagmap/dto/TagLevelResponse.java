package com.songpapeople.hashtagmap.dto;

import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.model.TagLevels;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return IntStream.range(0, tagLevels.getTagLevelsSize())
                .mapToObj(index -> {
                    TagLevel tagLevel = tagLevels.get(index);
                    return new TagLevelResponse(tagLevel.getId(),
                            tagLevel.getMinHashtagCount(), tagLevel.getMaxHashtagCount());
                })
                .collect(Collectors.toList());
    }
}
