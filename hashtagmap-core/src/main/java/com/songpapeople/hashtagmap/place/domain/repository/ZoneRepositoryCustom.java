package com.songpapeople.hashtagmap.place.domain.repository;

import com.songpapeople.hashtagmap.place.domain.model.Zone;

import java.util.List;

public interface ZoneRepositoryCustom {
    List<Zone> findByActivatedDistrict();
}