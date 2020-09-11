package com.songpapeople.hashtagmap.event.repository;

import com.songpapeople.hashtagmap.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventHistoryRepository extends JpaRepository<Event, Long> {
}
