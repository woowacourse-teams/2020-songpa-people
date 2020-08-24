package com.songpapeople.hashtagmap.instagrampost.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.CommonExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstagramPostCommandService {
    private final InstagramPostRepository instagramPostRepository;
    private final InstagramRepository instagramRepository;
    private final PlaceRepository placeRepository;

    public InstagramPostCommandService(InstagramPostRepository instagramPostRepository, InstagramRepository instagramRepository, PlaceRepository placeRepository) {
        this.instagramPostRepository = instagramPostRepository;
        this.instagramRepository = instagramRepository;
        this.placeRepository = placeRepository;
    }

    @Transactional
    public void deleteByInstagramId(BlackListRequest blackListRequest) {
        Instagram instagram = findInstagramByPlaceId(blackListRequest.getPlaceId());
        instagramPostRepository.deleteByInstagramId(instagram.getId());
    }

    public Instagram findInstagramByPlaceId(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new AdminException(
                        CommonExceptionStatus.NOT_PERSIST,
                        String.format("존재하지 않는 placeId(%d)입니다.", placeId)));
        return instagramRepository.findByPlaceFetch(place);
    }
}
