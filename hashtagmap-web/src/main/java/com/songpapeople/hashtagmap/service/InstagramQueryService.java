package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.exception.WebException;
import com.songpapeople.hashtagmap.exception.WebExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
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
            TagLevel tagLevel = findTagLevelByInstagram(tagLevels, instagram);
            markerResponses.add(MarkerResponse.of(instagram, tagLevel.getId()));
        }
        return markerResponses;
    }

    private TagLevel findTagLevelByInstagram(List<TagLevel> tagLevels, Instagram instagram) {
        Long hashtagCount = instagram.getHashtagCount();
        for (TagLevel tagLevel : tagLevels) {
            if (tagLevel.contains(hashtagCount)) {
                return tagLevel;
            }
        }
        throw new WebException(WebExceptionStatus.INVALID_TAG_LEVEL); // Todo how?
    }
}
