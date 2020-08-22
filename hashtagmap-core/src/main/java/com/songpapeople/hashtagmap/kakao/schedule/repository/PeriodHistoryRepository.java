package com.songpapeople.hashtagmap.kakao.schedule.repository;

import com.songpapeople.hashtagmap.kakao.schedule.model.PeriodHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodHistoryRepository extends JpaRepository<PeriodHistory, Long> {
    Optional<PeriodHistory> findTopByOrderByChangedDateDesc();
}