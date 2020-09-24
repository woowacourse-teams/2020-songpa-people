package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.MockDataFactory;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CrawlingResultTest {
    private Place place;
    private CrawlingResult crawlingResult;

    @BeforeEach
    void setUp() {
        CrawlingDto crawlingDto = CrawlingDto.of("스타벅스", String.valueOf(CrawlingResult.MIN_HASHTAG_COUNT),
                MockDataFactory.createPostDtos());
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

    @DisplayName("해시태그 개수가 기준 이하일 때 예외가 발생한다.")
    @Test
    void validateHashtagCountTest() {
        final int NOT_ENOUGH_HASHTAG_COUNT = 100;
        CrawlingDto crawlingDto = CrawlingDto.of("해시태그_이름", String.valueOf(NOT_ENOUGH_HASHTAG_COUNT), null);
        InstagramSchedulerException actual = assertThrows(InstagramSchedulerException.class, () -> new CrawlingResult(crawlingDto, null));

        InstagramSchedulerException expected = new InstagramSchedulerException(InstagramSchedulerExceptionStatus.NOT_ENOUGH_HASHTAG_COUNT);

        assertThat(actual.getErrorCode()).isEqualTo(expected.getErrorCode());
        assertThat(actual.getErrorMessage()).isEqualTo(expected.getErrorMessage());
    }
}
