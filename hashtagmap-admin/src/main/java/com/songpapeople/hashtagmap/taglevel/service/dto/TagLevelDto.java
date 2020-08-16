package com.songpapeople.hashtagmap.taglevel.service.dto;

import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagLevelDto {
    Long tagLevel;
    Long minHashtagCount;
    Long maxHashtagCount;

    public static TagLevelDto from(TagLevel tagLevel) {
        return new TagLevelDto(tagLevel.getId(), tagLevel.getMinHashtagCount(), tagLevel.getMaxHashtagCount());
    }
}
