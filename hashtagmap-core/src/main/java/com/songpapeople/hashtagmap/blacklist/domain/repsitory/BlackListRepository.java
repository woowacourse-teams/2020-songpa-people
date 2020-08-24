package com.songpapeople.hashtagmap.blacklist.domain.repsitory;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    Optional<BlackList> findByKakaoId(String kakaoId);
}
