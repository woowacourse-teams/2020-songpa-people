package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.place.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InstagramScheduleServiceTest {
    @Autowired
    private PlaceRepository placeRepository;

    @BeforeAll
    static void beforeAll() {
    }
}
