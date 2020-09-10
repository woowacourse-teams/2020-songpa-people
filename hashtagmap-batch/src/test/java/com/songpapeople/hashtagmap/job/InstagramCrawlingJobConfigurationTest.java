package com.songpapeople.hashtagmap.job;

import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringBootTest
public class InstagramCrawlingJobConfigurationTest {

    @Autowired
    private JpaPagingItemReader<Place> reader;

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("reader: 데이터 조회")
    @Test
    void readerTest() throws Exception {
        // given
        Place place1 = Place.builder()
                .id(1L)
                .build();
        Place place2 = Place.builder()
                .id(2L)
                .build();
        placeRepository.saveAll(Arrays.asList(place1, place2));

        // when, then
        reader.open(new ExecutionContext());
        assertThat(reader.read().getId()).isEqualTo(1L);
        assertThat(reader.read().getId()).isEqualTo(2L);
        assertThat(reader.getPageSize()).isEqualTo(2);

        reader.close();
    }

    @AfterEach
    private void tearDown() {
        placeRepository.deleteAllInBatch();
    }
}