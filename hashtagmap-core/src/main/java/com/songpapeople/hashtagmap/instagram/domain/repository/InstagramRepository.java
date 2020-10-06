package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface InstagramRepository extends JpaRepository<Instagram, Long> {

    @Transactional
    void deleteByPlaceId(Long placeId);
}
