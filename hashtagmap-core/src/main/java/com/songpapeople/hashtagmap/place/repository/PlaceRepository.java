package com.songpapeople.hashtagmap.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.songpapeople.hashtagmap.place.domain.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
