package com.songpapeople.hashtagmap.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.dto.InstagramPostResponse;
import com.songpapeople.hashtagmap.dto.QInstagramPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagram.instagram;
import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagramPost.instagramPost;

@RequiredArgsConstructor
@Repository
public class InstagramPostWebQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<InstagramPostResponse> findAllByInstagramId(Long id) {
        return jpaQueryFactory.select(new QInstagramPostResponse(
                instagramPost.id,
                instagramPost.imageUrl,
                instagramPost.postUrl
        ))
                .from(instagramPost)
                .where(instagram.id.eq(id))
                .fetch();
    }
}
