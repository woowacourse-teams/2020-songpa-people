package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.instagram.domain.model.dto.InstagramForBlacklist;
import com.songpapeople.hashtagmap.instagram.domain.model.dto.InstagramForMaker;
import com.songpapeople.hashtagmap.instagram.domain.model.dto.InstagramForUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagram.instagram;

@RequiredArgsConstructor
@Repository
public class InstagramQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<InstagramForMaker> findAllFetch() {
        return jpaQueryFactory.select(Projections.constructor(InstagramForMaker.class,
                instagram.id,
                instagram.hashtagCount,
                instagram.hashtagName,
                instagram.place.placeName,
                instagram.place.placeUrl,
                instagram.place.kakaoId,
                instagram.place.location,
                instagram.place.category))
                .from(instagram)
                .fetch();
    }

    public List<InstagramForBlacklist> findAllOrderByHashtagCountAndLimitBy(int limit) {
        return jpaQueryFactory.select(Projections.constructor(InstagramForBlacklist.class,
                instagram.hashtagName,
                instagram.hashtagCount,
                instagram.place.kakaoId,
                instagram.place.placeName,
                instagram.place.location.roadAddressName
        ))
                .from(instagram)
                .orderBy(instagram.hashtagCount.desc())
                .limit(limit)
                .fetch();
    }

    public InstagramForUpdate findByKakaoId(String kakaoId) {
        return jpaQueryFactory.select(Projections.constructor(InstagramForUpdate.class,
                instagram.id,
                instagram.place.id.as("placeId")))
                .from(instagram)
                .where(instagram.place.kakaoId.eq(kakaoId))
                .fetchFirst();
    }

    public List<Long> findAllHashtagCountByOrderAsc() {
        return jpaQueryFactory.select(instagram.hashtagCount)
                .from(instagram)
                .orderBy(instagram.hashtagCount.asc())
                .fetch();
    }

}
