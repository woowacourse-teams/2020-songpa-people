package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.InstagramPost.InstagramPostResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstagramPostQueryService {
    private final InstagramPostRepository instagramPostRepository;

    public InstagramPostQueryService(InstagramPostRepository instagramPostRepository) {
        this.instagramPostRepository = instagramPostRepository;
    }

    public List<InstagramPostResponse> findAllByInstagramId(Long id) {
        List<InstagramPost> instagramPosts = instagramPostRepository.findAllByInstagramId(id);
        return instagramPosts.stream()
                .map(InstagramPostResponse::of)
                .collect(Collectors.toList());
    }
}
