package com.songpapeople.hashtagmap.taglevel.service.dto;

import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagLevelDto {
    private Long tagLevel;
    private Long minHashtagCount;
    private Long maxHashtagCount;

    public static TagLevelDto from(TagLevel tagLevel) {
        return new TagLevelDto(tagLevel.getId(), tagLevel.getMinHashtagCount(), tagLevel.getMaxHashtagCount());
    }
}
