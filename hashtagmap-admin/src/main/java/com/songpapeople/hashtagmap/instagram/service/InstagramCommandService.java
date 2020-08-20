package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddRequest;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import org.springframework.stereotype.Service;

@Service
public class InstagramCommandService {
    private final InstagramRepository instagramRepository;

    public InstagramCommandService(InstagramRepository instagramRepository) {
        this.instagramRepository = instagramRepository;
    }

    public void updateByBlackList(BlackListAddRequest blackListRequest) {
        
    }
}
