package com.songpapeople.hashtagmap.place.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.place.domain.model.QZone;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ZoneQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Zone> findByActivated() {
        QZone zone = QZone.zone;
        return jpaQueryFactory.selectFrom(zone)
                .where(zone.isActivated.isTrue())
                .fetch();
    }
}
