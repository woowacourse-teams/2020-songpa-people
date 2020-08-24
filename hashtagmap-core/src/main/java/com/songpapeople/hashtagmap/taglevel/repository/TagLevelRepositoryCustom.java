package com.songpapeople.hashtagmap.taglevel.repository;

import com.songpapeople.hashtagmap.taglevel.model.TagLevel;

import java.util.List;

public interface TagLevelRepositoryCustom {
    List<TagLevel> findFiveByModifiedDateOrderById();
}
