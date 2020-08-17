package com.songpapeople.hashtagmap.kakao.schedule.repository;

import com.songpapeople.hashtagmap.kakao.schedule.model.PeriodHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodHistoryRepository extends JpaRepository<PeriodHistory, Long> {
}