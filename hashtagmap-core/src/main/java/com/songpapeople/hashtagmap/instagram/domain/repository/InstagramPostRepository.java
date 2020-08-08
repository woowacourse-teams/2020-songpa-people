package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstagramPostRepository extends JpaRepository<InstagramPost, Long> {
}
