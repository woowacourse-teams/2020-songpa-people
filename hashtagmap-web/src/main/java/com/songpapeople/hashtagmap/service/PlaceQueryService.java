package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceQueryService {
    private final PlaceRepository placeRepository;

    public PlaceQueryService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }
}
