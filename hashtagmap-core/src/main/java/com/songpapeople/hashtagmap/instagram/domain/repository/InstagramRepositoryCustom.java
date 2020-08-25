package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;

import java.util.List;

public interface InstagramRepositoryCustom {
    List<Instagram> findAllFetch();

    Instagram findByIdFetch(Long id);

    Instagram findByPlaceFetch(Place place);

    List<Long> findAllHashtagCountByOrderAsc();

    Instagram findByKakaoId(String kakaoId);

    List<Instagram> findAllOrderByHashtagCountAndLimitBy(int limit);
}
