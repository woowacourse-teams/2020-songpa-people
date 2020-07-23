package com.songpapeople.hashtagmap.mapper;

import com.songpapeople.hashtagmap.SchedulerTestResource;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class MapperTest extends SchedulerTestResource {
    private PostDtos postDtos;

    @BeforeEach
    void setUp() {
        postDtos = createPostDtos();
    }

    @DisplayName("CrawlingDto를 Instagram으로 변환 테스트")
    @Test
    void toInstagram() {
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

    @DisplayName("postDto를 InstagramPost로 변환 테스트")
    @Test
    void toInstagramPost() {
        Place place = Place.builder()
                .placeName("스타벅스")
                .build();
        Instagram instagram = Instagram.builder()
                .hashtagName("스타벅스")
                .hashtagCount(100L)
                .place(place)
                .build();
        InstagramPost expected = InstagramPost.builder()
                .instagram(instagram)
                .postUrl("https://www.instagram.com/p/postUrl")
                .imageUrl("imageUrl")
                .build();

        PostDto postDto = new PostDto("postUrl", "imageUrl");

        assertThat(Mapper.toInstagramPost(postDto, instagram)).isEqualToComparingFieldByField(expected);
    }
}
