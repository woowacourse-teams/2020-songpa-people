package com.songpapeople.hashtagmap.taglevel.service.dto;

import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import lombok.Getter;

@Getter
public class TagLevelDto {
    private Long tagLevel;
    private Long minHashtagCount;
    private Long maxHashtagCount;

    public TagLevelDto(Long tagLevel, Long minHashtagCount, Long maxHashtagCount) {
        this.tagLevel = tagLevel;
        this.minHashtagCount = minHashtagCount;
        this.maxHashtagCount = maxHashtagCount;
    }

    public static TagLevelDto from(TagLevel tagLevel) {
        return new TagLevelDto(tagLevel.getId(), tagLevel.getMinHashtagCount(), tagLevel.getMaxHashtagCount());
    }
}
