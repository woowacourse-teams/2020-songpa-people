package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InstagramCommandService {
    private final InstagramRepository instagramRepository;

    @Transactional
    public Instagram update(Instagram instagram,
                            String replaceName,
                            Long hashtagCount) {
        instagram.setHashtagName(replaceName);
        instagram.setHashtagCount(hashtagCount);
        return instagramRepository.save(instagram);
    }
}
