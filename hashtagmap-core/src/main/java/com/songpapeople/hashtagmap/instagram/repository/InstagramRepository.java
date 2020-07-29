package com.songpapeople.hashtagmap.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;

public interface InstagramRepository extends JpaRepository<Instagram, Long> {
}
