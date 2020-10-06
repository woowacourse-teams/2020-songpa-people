package com.songpapeople.hashtagmap.taglevel.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songpapeople.hashtagmap.taglevel.model.QTagLevel;
import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository

public class TagLevelQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<TagLevel> findFiveByModifiedDateOrderById() {
        QTagLevel tagLevel = QTagLevel.tagLevel;
        List<TagLevel> recentTagLevels = jpaQueryFactory.selectFrom(tagLevel)
                .orderBy(tagLevel.id.desc())
                .limit(5)
                .fetch();
        Collections.reverse(recentTagLevels);
        return recentTagLevels;
    }
}
