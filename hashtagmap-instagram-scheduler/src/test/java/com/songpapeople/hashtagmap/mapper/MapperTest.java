package com.songpapeople.hashtagmap.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import com.songpapeople.hashtagmap.SchedulerTestResource;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;

class MapperTest extends SchedulerTestResource {

    @Test
    void toInstagram() {
        PostDtos postDtos = createPostDtos();
        Place place = Place.builder()
            .placeName("스타벅스")
            .build();
        CrawlingDto crawlingDto = CrawlingDto.of("스타벅스", "100", postDtos);
        Instagram expected = Instagram.builder()
            .hashtagName("스타벅스")
            .hashtagCount(100L)
            .place(place)
            .build();

        assertThat(Mapper.toInstagram(crawlingDto, place)).isEqualToComparingFieldByField(expected);
    }
}