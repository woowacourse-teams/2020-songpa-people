package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InstagramCommandService {
    private final InstagramRepository instagramRepository;

    @Transactional
    public BlackListAddResponse updateByBlackList(Instagram instagram,
                                                  String replaceName,
                                                  Long hashtagCount) {
        instagram.setHashtagName(replaceName);
        instagram.setHashtagCount(hashtagCount);
        Instagram updated = instagramRepository.save(instagram);
        return BlackListAddResponse.of(updated);
    }
}
