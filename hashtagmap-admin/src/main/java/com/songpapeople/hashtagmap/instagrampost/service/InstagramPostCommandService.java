package com.songpapeople.hashtagmap.instagrampost.service;

import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstagramPostCommandService {
    private final InstagramPostRepository instagramPostRepository;

    public InstagramPostCommandService(InstagramPostRepository instagramPostRepository) {
        this.instagramPostRepository = instagramPostRepository;
    }

    @Transactional
    public void deleteByInstagramId(Long instagramId) {
        instagramPostRepository.deleteByInstagramId(instagramId);
    }
}
