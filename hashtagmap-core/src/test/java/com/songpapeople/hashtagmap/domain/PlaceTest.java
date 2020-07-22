package com.songpapeople.hashtagmap.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PlaceTest {
    @Autowired
    PlaceRepository placeRepository;

    @Test
    void save() {
        Place place = new Place("스타벅스");
        placeRepository.save(place);
    }
}