package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.MockDataFactory;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CrawlingResultTest {
    private CrawlingDto crawlingDto;
    private Place place;
    private CrawlingResult crawlingResult;

    @BeforeEach
    void setUp() {
        crawlingDto = CrawlingDto.of("스타벅스", String.valueOf(CrawlingResult.MIN_HASHTAG_COUNT), MockDataFactory.createPostDtos());
        place = Place.builder()
                .placeName("스타벅스")
                .build();

        crawlingResult = new CrawlingResult(crawlingDto, place);
    }

    @DisplayName("인스타그램 entity를 만드는 기능 테스트")
    @Test
    void toInstagram() {
        Instagram actual = crawlingResult.createInstagram();

        Instagram expected = Instagram.builder()
                .hashtagName("스타벅스")
                .hashtagCount(CrawlingResult.MIN_HASHTAG_COUNT)
                .place(place)
                .build();

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}
