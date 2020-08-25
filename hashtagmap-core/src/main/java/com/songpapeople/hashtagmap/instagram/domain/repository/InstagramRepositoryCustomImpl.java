package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;

import java.util.List;

import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagram.instagram;
import static com.songpapeople.hashtagmap.place.domain.model.QPlace.place;

public class InstagramRepositoryCustomImpl implements InstagramRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public InstagramRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Instagram> findAllFetch() {
        return jpaQueryFactory.selectFrom(instagram)
                .innerJoin(instagram.place, place)
                .fetchJoin()
                .fetch();
    }

    @Override
    public Instagram findByIdFetch(Long id) {
        return jpaQueryFactory.selectFrom(instagram)
                .innerJoin(instagram.place, place)
                .fetchJoin()
                .where(instagram.id.eq(id))
                .fetchFirst();
    }

    @Override
    public Instagram findByPlaceFetch(Place findPlace) {
        return jpaQueryFactory.selectFrom(instagram)
                .innerJoin(instagram.place, place)
                .fetchJoin()
                .where(instagram.place.eq(findPlace))
                .fetchFirst();
    }

    @Override
    public List<Long> findAllHashtagCountByOrderAsc() {
        return jpaQueryFactory.select(instagram.hashtagCount)
                .from(instagram)
                .orderBy(instagram.hashtagCount.asc())
                .fetch();
    }

    @Override
    public Instagram findByKakaoIdFetch(String kakaoId) {
        return jpaQueryFactory.selectFrom(instagram)
                .innerJoin(instagram.place, place)
                .fetchJoin()
                .where(place.kakaoId.eq(kakaoId))
                .fetchFirst();
    }

    @Override
    public List<Instagram> findAllOrderByHashtagCountAndLimitBy(int limit) {
        return jpaQueryFactory.selectFrom(instagram)
                .innerJoin(instagram.place, place)
                .fetchJoin()
                .orderBy(instagram.hashtagCount.desc())
                .limit(limit)
                .fetch();
    }
}
