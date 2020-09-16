package com.songpapeople.hashtagmap.event.repository;

import com.songpapeople.hashtagmap.event.model.EventHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventHistoryRepository<T extends EventHistory> extends JpaRepository<T, Long> {
}
