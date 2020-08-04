package com.songpapeople.hashtagmap.place.domain.repository;

import com.songpapeople.hashtagmap.place.domain.model.Place;

import java.util.List;

public interface PlaceRepositoryCustom {
    void updateAndInsert(List<Place> places);
}
