package com.songpapeople.hashtagmap.place.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.place.domain.model.QZone;
import com.songpapeople.hashtagmap.place.domain.model.Zone;

import java.util.List;

public class ZoneRepositoryCustomImpl implements ZoneRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ZoneRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Zone> findByActivated() {
        QZone zone = QZone.zone;
        return jpaQueryFactory.selectFrom(zone)
                .where(zone.isActivated.isTrue())
                .fetch();
    }
}
