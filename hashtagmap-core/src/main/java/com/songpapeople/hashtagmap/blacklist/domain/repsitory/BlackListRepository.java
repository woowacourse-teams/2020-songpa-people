package com.songpapeople.hashtagmap.blacklist.domain.repsitory;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
}
