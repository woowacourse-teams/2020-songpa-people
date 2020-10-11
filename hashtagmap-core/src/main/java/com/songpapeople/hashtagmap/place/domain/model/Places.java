package com.songpapeople.hashtagmap.place.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Places {
    private final List<Place> places;

    public Places(final List<Place> places) {
        this.places = new ArrayList<>(places);
    }

    public void insertPlaces(List<Place> places) {
        List<Place> insertablePlace = findInsertablePlace(places);
        this.places.addAll(insertablePlace);
    }

    private List<Place> findInsertablePlace(List<Place> insertPlaces) {
        return insertPlaces.stream()
                .filter(this::notContain)
                .collect(Collectors.toList());
    }

    public void updatePlaces(List<Place> updatePlaces) {
        for (Place updatePlace : updatePlaces) {
            for (Place place : this.places) {
                if (place.isEqualKakaoId(updatePlace)) {
                    place.update(updatePlace);
                }
            }
        }
    }

    private boolean notContain(Place place) {
        return places.stream()
                .noneMatch(aPlace -> aPlace.isEqualKakaoId(place));
    }

    public List<Place> get() {
        return Collections.unmodifiableList(this.places);
    }
}
