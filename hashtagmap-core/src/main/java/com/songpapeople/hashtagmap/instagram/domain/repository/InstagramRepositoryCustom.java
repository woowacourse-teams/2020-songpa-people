package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;

import java.util.List;

public interface InstagramRepositoryCustom {
    List<Instagram> findAllFetch();

    List<Long> findAllHashtagCountByOrderAsc();
}
