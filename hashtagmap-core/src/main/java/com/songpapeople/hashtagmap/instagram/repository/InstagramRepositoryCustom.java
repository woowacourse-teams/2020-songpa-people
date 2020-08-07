package com.songpapeople.hashtagmap.instagram.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;

import java.util.List;

public interface InstagramRepositoryCustom {
    List<Instagram> findAllFetch();
}
