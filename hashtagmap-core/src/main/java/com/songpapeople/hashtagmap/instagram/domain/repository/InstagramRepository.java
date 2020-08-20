package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstagramRepository extends JpaRepository<Instagram, Long>, InstagramRepositoryCustom {
}
