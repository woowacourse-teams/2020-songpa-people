package com.songpapeople.hashtagmap.taglevel.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TagLevelFinder {

    public static Long findTagLevelIdByHashtagCount(List<TagLevel> tagLevels, Long hashtagCount) {
        for (TagLevel tagLevel : tagLevels) {
            if (tagLevel.contains(hashtagCount)) {
                return tagLevel.getId();
            }
        }
        log.info(String.format("CoreException:태그레벨에 맞지 않는 hashtagCount(%s)가 존재합니다.", hashtagCount));
        return tagLevels.get(0).getId();
    }

}
