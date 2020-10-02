package com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;

import java.util.List;

public interface InstagramPostRepositoryCustom {
    List<InstagramPost> findAllByInstagramId(Long id);
}
