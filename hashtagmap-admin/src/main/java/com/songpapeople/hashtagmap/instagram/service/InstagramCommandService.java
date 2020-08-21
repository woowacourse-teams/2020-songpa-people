package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstagramCommandService {
    private final InstagramRepository instagramRepository;

    public InstagramCommandService(InstagramRepository instagramRepository) {
        this.instagramRepository = instagramRepository;
    }

    @Transactional
    public void delete(Instagram instagram) {
        instagramRepository.delete(instagram);
    }
}
