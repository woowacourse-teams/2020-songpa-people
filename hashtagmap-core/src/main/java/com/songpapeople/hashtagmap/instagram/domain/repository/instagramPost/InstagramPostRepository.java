package com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface InstagramPostRepository extends JpaRepository<InstagramPost, Long> {
    @Transactional
    void deleteByInstagramId(Long instagramId);
}
