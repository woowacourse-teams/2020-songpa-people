package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.InstagramPost.InstagramPostResponse;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramPostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstagramPostQueryService {
    private final InstagramPostRepository instagramPostRepository;

    public InstagramPostQueryService(InstagramPostRepository instagramPostRepository) {
        this.instagramPostRepository = instagramPostRepository;
    }

    public List<InstagramPostResponse> findAllByInstagramId(Long id) {
        return new ArrayList<>();
    }
}
