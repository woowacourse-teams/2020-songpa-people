package com.songpapeople.hashtagmap.scheduler;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.songpapeople.hashtagmap.SchedulerTestResource;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.place.domain.model.Place;

class CrawlingResultTest extends SchedulerTestResource {
    private CrawlingDto crawlingDto;
    private Place place;
    private CrawlingResult crawlingResult;

    @BeforeEach
    void setUp() {
        crawlingDto = CrawlingDto.of("스타벅스", "10", createPostDtos());
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
            .hashtagCount(100L)
                .place(place)
                .build();

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }


    @Test
    void toInstagramPosts() {
        List<InstagramPost> expected = crawlingResult.toInstagramPosts();

        List<InstagramPost> actual = crawlingResult.toInstagramPosts();
    }
}
