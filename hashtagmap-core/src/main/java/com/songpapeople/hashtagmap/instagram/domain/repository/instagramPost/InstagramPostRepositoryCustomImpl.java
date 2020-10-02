package com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;

import java.util.List;

import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagram.instagram;
import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagramPost.instagramPost;

public class InstagramPostRepositoryCustomImpl implements InstagramPostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public InstagramPostRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<InstagramPost> findAllByInstagramId(Long id) {
        return jpaQueryFactory.selectFrom(instagramPost)
                .innerJoin(instagramPost.instagram, instagram)
                .fetchJoin()
                .where(instagram.id.eq((long) id))
                .fetch();
    }
}
