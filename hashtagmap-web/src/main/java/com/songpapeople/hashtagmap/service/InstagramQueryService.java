package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.model.TagLevelFinder;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InstagramQueryService {
    private final InstagramRepository instagramRepository;
    private final TagLevelRepository tagLevelRepository;

    public List<MarkerResponse> findAllMarkers() {
        List<Instagram> instagrams = instagramRepository.findAllFetch();
        List<TagLevel> tagLevels = tagLevelRepository.findAll();

        List<MarkerResponse> markerResponses = new ArrayList<>();
        for (Instagram instagram : instagrams) {
            Long tagLevelId = TagLevelFinder.findTagLevelIdByHashtagCount(tagLevels, instagram.getHashtagCount());
            markerResponses.add(MarkerResponse.of(instagram, tagLevelId));
        }
        return markerResponses;
    }
}
