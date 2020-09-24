package com.songpapeople.hashtagmap.instagram.processor;

import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.service.CrawlingResult;
import com.songpapeople.hashtagmap.service.InstagramScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstagramBatchProcessorTest {
    private InstagramBatchProcessor instagramBatchProcessor;

    @Mock
    private InstagramScheduleService instagramScheduleService;

    @BeforeEach
    private void setUp() {
        this.instagramBatchProcessor = new InstagramBatchProcessor(instagramScheduleService);
    }

    @DisplayName("읽어온 Place에 대해 크롤링 테스트")
    @Test
    public void placeProcessorTest() {
        // given
        Place place = Place.builder()
                .id(1L)
                .build();
        Optional<CrawlingResult> expected = Optional.of(new CrawlingResult());
        when(instagramScheduleService.createCrawlingResult(place)).thenReturn(expected);

        // when
        Optional<CrawlingResult> actual = instagramBatchProcessor.process(place);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
