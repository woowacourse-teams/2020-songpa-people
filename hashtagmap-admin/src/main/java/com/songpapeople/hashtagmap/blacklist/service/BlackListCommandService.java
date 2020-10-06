package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramQueryRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BlackListCommandService {
    private final BlackListRepository blackListRepository;
    private final InstagramRepository instagramRepository;
    private final InstagramQueryRepository instagramQueryRepository;
    private final InstagramPostRepository instagramPostRepository;

    @Transactional
    public void deleteInstagramAfterAddBlackList(BlackListRequest blackListRequest) {
        blackListRepository.save(BlackListRequest.toSkipBlackList(blackListRequest));
        Instagram instagramToDelete = instagramQueryRepository.findByKakaoIdFetch(blackListRequest.getKakaoId());
        instagramPostRepository.deleteByInstagramId(instagramToDelete.getId());
        instagramRepository.delete(instagramToDelete);
    }
}
