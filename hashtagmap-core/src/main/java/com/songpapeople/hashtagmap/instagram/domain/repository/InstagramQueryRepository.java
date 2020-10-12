package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagram.instagram;
import static com.songpapeople.hashtagmap.place.domain.model.QPlace.place;

@RequiredArgsConstructor
@Repository
public class InstagramQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Long> findAllHashtagCountByOrderAsc() {
        return jpaQueryFactory.select(instagram.hashtagCount)
                .from(instagram)
                .orderBy(instagram.hashtagCount.asc())
                .fetch();
    }

    public Instagram findByKakaoId(String kakaoId) {
        return jpaQueryFactory.selectFrom(instagram)
                .innerJoin(instagram.place, place)
                .fetchJoin()
                .where(place.kakaoId.eq(kakaoId))
                .fetchFirst();

    }

}
