package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.SemiBlackListDto;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.CommonExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InstagramQueryService {
    public static final int SUB_BLACK_LIST_SIZE = 20;

    private final InstagramRepository instagramRepository;
    private final PlaceRepository placeRepository;

    public List<SemiBlackListDto> findSemiBlackListInstagram() {
        List<Instagram> instagrams = instagramRepository.findAllFetch();
        return instagrams.stream()
                .sorted(Comparator.comparingDouble(Instagram::getHashtagCount).reversed())
                .limit(SUB_BLACK_LIST_SIZE)
                .map(SemiBlackListDto::of)
                .collect(Collectors.toList());
    }

    public Instagram findByPlaceId(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new AdminException(
                        CommonExceptionStatus.NOT_PERSIST,
                        String.format("존재하지 않는 placeId(%d)입니다.", placeId)));
        return instagramRepository.findByPlaceFetch(place);
    }
}
