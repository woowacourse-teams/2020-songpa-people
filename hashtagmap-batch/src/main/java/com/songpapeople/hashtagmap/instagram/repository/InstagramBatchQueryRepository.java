package com.songpapeople.hashtagmap.instagram.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.instagram.repository.dto.InstagramBatchDto;
import com.songpapeople.hashtagmap.instagram.repository.dto.QInstagramBatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.songpapeople.hashtagmap.instagram.domain.model.QInstagram.instagram;

@RequiredArgsConstructor
@Repository
public class InstagramBatchQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<InstagramBatchDto> findAll() {
        return jpaQueryFactory.select(new QInstagramBatchDto(
                instagram.id,
                instagram.place.id
        ))
                .from(instagram)
                .fetch();
    }
}
