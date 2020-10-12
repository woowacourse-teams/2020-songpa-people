package com.songpapeople.hashtagmap.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.dto.InstagramForMarker;
import com.songpapeople.hashtagmap.dto.QInstagramForMarker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagram.instagram;

@RequiredArgsConstructor
@Repository
public class InstagramWebQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<InstagramForMarker> findAllFetch() {
        return jpaQueryFactory.select(new QInstagramForMarker(
                instagram.id,
                instagram.hashtagCount,
                instagram.hashtagName,
                instagram.place.placeName,
                instagram.place.placeUrl,
                instagram.place.kakaoId,
                instagram.place.location,
                instagram.place.category
        ))
                .from(instagram)
                .fetch();
    }
}
