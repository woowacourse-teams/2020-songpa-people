package com.songpapeople.hashtagmap.kakao.schedule.repository;

import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByTarget(String target);
}
