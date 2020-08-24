package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
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
public class BlackListCommandService {
    private final BlackListRepository blackListRepository;
    private final PlaceRepository placeRepository;
    private final InstagramRepository instagramRepository;
    private final InstagramPostRepository instagramPostRepository;

    public BlackListCommandService(BlackListRepository blackListRepository, PlaceRepository placeRepository, InstagramRepository instagramRepository, InstagramPostRepository instagramPostRepository) {
        this.blackListRepository = blackListRepository;
        this.placeRepository = placeRepository;
        this.instagramRepository = instagramRepository;
        this.instagramPostRepository = instagramPostRepository;
    }

    @Transactional
    public void deleteInstagramAfterAddBlackList(BlackListRequest blackListRequest) {
        blackListRepository.save(BlackListRequest.toSkipBlackList(blackListRequest));
        Instagram instagramToDelete = findInstagramByPlaceId(blackListRequest.getPlaceId());
        instagramPostRepository.deleteByInstagramId(instagramToDelete.getId());
        instagramRepository.delete(instagramToDelete);
    }

    public Instagram findInstagramByPlaceId(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new AdminException(
                        CommonExceptionStatus.NOT_PERSIST,
                        String.format("존재하지 않는 placeId(%d)입니다.", placeId)));
        return instagramRepository.findByPlaceFetch(place);
    }
}
