package com.songpapeople.hashtagmap.place.domain.repository;

import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
