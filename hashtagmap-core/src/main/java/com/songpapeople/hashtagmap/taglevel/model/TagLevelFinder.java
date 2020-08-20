package com.songpapeople.hashtagmap.taglevel.model;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TagLevelFinder {

    private static final long FIRST_TAG_LEVEL_ID = 1L;

    public static Long findTagLevelIdByHashtagCount(List<TagLevel> tagLevels, Long hashtagCount) {
        for (TagLevel tagLevel : tagLevels) {
            if (tagLevel.isContains(hashtagCount)) {
                return tagLevel.getId();
            }
        }
        log.info(String.format("CoreException:태그레벨에 맞지 않는 hashtagCount(%s)가 존재합니다.", hashtagCount));
        return FIRST_TAG_LEVEL_ID;
    }

}
