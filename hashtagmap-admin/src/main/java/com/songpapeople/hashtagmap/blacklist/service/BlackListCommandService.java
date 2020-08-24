package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlackListCommandService {
    private final BlackListRepository blackListRepository;
    private final InstagramRepository instagramRepository;
    private final InstagramPostRepository instagramPostRepository;

    public BlackListCommandService(BlackListRepository blackListRepository
            , InstagramRepository instagramRepository
            , InstagramPostRepository instagramPostRepository) {
        this.blackListRepository = blackListRepository;
        this.instagramRepository = instagramRepository;
        this.instagramPostRepository = instagramPostRepository;
    }

    @Transactional
    public void deleteInstagramAfterAddBlackList(BlackListRequest blackListRequest) {
        blackListRepository.save(BlackListRequest.toSkipBlackList(blackListRequest));
        Instagram instagramToDelete = instagramRepository.findByKakaoId(blackListRequest.getKakaoId());
        instagramPostRepository.deleteByInstagramId(instagramToDelete.getId());
        instagramRepository.delete(instagramToDelete);
    }
}
