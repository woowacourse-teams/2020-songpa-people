package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramQueryRepository;
import com.songpapeople.hashtagmap.taglevel.model.TagLevels;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InstagramQueryService {
    private final InstagramQueryRepository instagramQueryRepository;
    private final TagLevelRepository tagLevelRepository;

    public List<MarkerResponse> findAllMarkers() {
        List<Instagram> instagrams = instagramQueryRepository.findAllFetch();
        TagLevels tagLevels = new TagLevels(tagLevelRepository.findAll());

        List<MarkerResponse> markerResponses = new ArrayList<>();
        for (Instagram instagram : instagrams) {
            Long tagLevelId = tagLevels.findIdByHashtagCount(instagram.getHashtagCount());
            markerResponses.add(MarkerResponse.of(instagram, tagLevelId));
        }
        return markerResponses;
    }
}
