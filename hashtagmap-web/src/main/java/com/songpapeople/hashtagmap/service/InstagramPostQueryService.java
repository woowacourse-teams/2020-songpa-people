package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.InstagramPost.InstagramPostResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstagramPostQueryService {
    public List<InstagramPostResponse> findAllByInstagramId(Long id) {
        return new ArrayList<>();
    }
}
