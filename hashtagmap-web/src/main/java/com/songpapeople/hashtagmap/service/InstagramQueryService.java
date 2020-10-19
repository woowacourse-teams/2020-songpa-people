package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.InstagramForMarker;
import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.repository.InstagramWebQueryRepository;
import com.songpapeople.hashtagmap.taglevel.model.TagLevels;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InstagramQueryService {
    private final InstagramWebQueryRepository instagramWebQueryRepository;
    private final TagLevelRepository tagLevelRepository;

    public List<MarkerResponse> findAllMarkers() {
        List<InstagramForMarker> instagramForMarkers = instagramWebQueryRepository.findAllFetch();
        List<Instagram> instagrams = instagramForMarkers.stream()
                .map(InstagramForMarker::toInstagram)
                .collect(Collectors.toList());

        TagLevels tagLevels = new TagLevels(tagLevelRepository.findAll());
        List<MarkerResponse> markerResponses = new ArrayList<>();
        for (Instagram instagram : instagrams) {
            Long tagLevelId = tagLevels.findIdByHashtagCount(instagram.getHashtagCount());
            markerResponses.add(MarkerResponse.of(instagram, tagLevelId));
        }
        return markerResponses;
    }

}
