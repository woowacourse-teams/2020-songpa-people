package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.InstagramPostResponse;
import com.songpapeople.hashtagmap.repository.InstagramPostWebQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InstagramPostQueryService {
    private final InstagramPostWebQueryRepository instagramPostWebQueryRepository;

    public List<InstagramPostResponse> findAllByInstagramId(Long id) {
        return instagramPostWebQueryRepository.findAllByInstagramId(id);
    }
}
