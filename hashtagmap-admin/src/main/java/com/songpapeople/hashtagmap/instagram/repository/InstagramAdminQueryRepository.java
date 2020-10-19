package com.songpapeople.hashtagmap.instagram.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.instagram.dto.InstagramForBlacklist;
import com.songpapeople.hashtagmap.instagram.dto.QInstagramForBlacklist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagram.instagram;

@RequiredArgsConstructor
@Repository
public class InstagramAdminQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<InstagramForBlacklist> findAllOrderByHashtagCountAndLimitBy(int limit) {
        return jpaQueryFactory.select(new QInstagramForBlacklist(
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
}
