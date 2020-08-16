package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstagramRepository extends JpaRepository<Instagram, Long>, InstagramRepositoryCustom {
    @Query(value = "select min(hashtag_count), max(hashtag_count) "
            + "from (select *, ntile(:tileSize) over (order by hashtag_count asc) tile_group from instagram) temp "
            + "group by temp.tile_group", nativeQuery = true)
    List<List<Long>> findTiledHashtagCount(@Param("tileSize") Integer tileSize);
    // todo List<Long>을 Object로 묶기
    // todo native Query 말고 다른 방식으로 해보기
}