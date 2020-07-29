package com.songpapeople.hashtagmap.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;

public interface InstagramPostRepository extends JpaRepository<InstagramPost, Long> {
}
