package com.songpapeople.hashtagmap.place.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.place.domain.model.QDistrict;
import com.songpapeople.hashtagmap.place.domain.model.QZone;
import com.songpapeople.hashtagmap.place.domain.model.Zone;

import java.util.List;

public class ZoneRepositoryCustomImpl implements ZoneRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ZoneRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Zone> findByActivatedDistrict() {
        QZone zone = QZone.zone;
        QDistrict district = QDistrict.district1;
        return jpaQueryFactory.select(zone)
                .from(district)
                .innerJoin(zone.district, district)
                .on(district.id.eq(zone.district.id))
                .fetch();
    }
}
