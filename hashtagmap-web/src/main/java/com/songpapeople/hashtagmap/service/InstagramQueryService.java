package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.instagram.repository.InstagramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstagramQueryService {
    private final InstagramRepository instagramRepository;

    public InstagramQueryService(InstagramRepository instagramRepository) {
        this.instagramRepository = instagramRepository;
    }

    public List<MarkerResponse> findAllMarkers() {
        return instagramRepository.findAll()
                .stream()
                .map(MarkerResponse::from)
                .collect(Collectors.toList());
    }
}
