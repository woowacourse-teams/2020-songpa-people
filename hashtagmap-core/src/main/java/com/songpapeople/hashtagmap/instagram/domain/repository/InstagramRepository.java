package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstagramRepository extends JpaRepository<Instagram, Long>, InstagramRepositoryCustom {
    Optional<Instagram> findByPlace(Place place);
}
