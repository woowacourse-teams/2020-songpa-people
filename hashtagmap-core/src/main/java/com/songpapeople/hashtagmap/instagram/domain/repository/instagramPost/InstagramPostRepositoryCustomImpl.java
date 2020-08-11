package com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;

import java.util.ArrayList;
import java.util.List;

public class InstagramPostRepositoryCustomImpl implements InstagramPostRepositoryCustom {
    @Override
    public List<InstagramPost> findAllByInstagramId(Long id) {
        return new ArrayList<>();
    }
}
