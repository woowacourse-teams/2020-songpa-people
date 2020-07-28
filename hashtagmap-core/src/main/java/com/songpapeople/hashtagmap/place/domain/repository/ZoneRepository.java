package com.songpapeople.hashtagmap.place.domain.repository;

import com.songpapeople.hashtagmap.place.domain.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long>, ZoneRepositoryCustom {
}
