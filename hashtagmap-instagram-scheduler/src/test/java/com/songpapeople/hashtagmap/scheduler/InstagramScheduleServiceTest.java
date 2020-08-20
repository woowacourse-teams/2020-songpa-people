package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InstagramScheduleServiceTest {
    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramScheduleService instagramScheduleService;

    @MockBean
    private InstagramCrawler instagramCrawler;

    @Test
    void updateBlackLists() {
        Place place = Place.builder()
                .placeName("place")
                .kakaoId("1")
                .category(Category.RESTAURANT)
                .placeUrl("placeUrl")
                .location(new Location(new Point("40", "130"), "address"))
                .build();
        Instagram oldInstagram = Instagram.builder()
                .place(place)
                .hashtagName("oldName")
                .hashtagCount(99999L)
                .build();
        PostDtos oldPostDtos = createMockPostDtosByStartIndex(1);
        placeRepository.save(place);
        instagramRepository.save(oldInstagram);
        instagramPostRepository.saveAll(makeInstagramPosts(oldInstagram, oldPostDtos));

        String replaceName = "replaceName";
        String newHashtagCount = "1000";

        PostDtos newPostDtos = createMockPostDtosByStartIndex(100);
        CrawlingDto crawlingDto = CrawlingDto.of(replaceName, newHashtagCount, newPostDtos);
        when(instagramCrawler.crawler(any())).thenReturn(crawlingDto);

        instagramScheduleService.updateBlackLists(replaceName, oldInstagram);

        Instagram newInstagram = instagramRepository.findById(oldInstagram.getId()).get();
        List<String> newPostImageUrls = instagramPostRepository.findAllByInstagramId(oldInstagram.getId()).stream()
                .map(InstagramPost::getImageUrl)
                .collect(Collectors.toList());

        assertAll(
                () -> assertThat(newInstagram.getPlaceId()).isEqualTo(place.getId()),
                () -> assertThat(newInstagram.getHashtagName()).isEqualTo(replaceName),
                () -> assertThat(newInstagram.getHashtagCount()).isEqualTo(Long.parseLong(newHashtagCount)),
                () -> assertThat(newPostImageUrls.contains("100")).isTrue(),
                () -> assertThat(newPostImageUrls.contains("1")).isFalse()
        );
    }

    private List<InstagramPost> makeInstagramPosts(Instagram instagram, PostDtos postDtos) {
        return postDtos.getPostDtos().stream()
                .map(postDto -> InstagramPost.builder()
                        .instagramId(instagram.getId())
                        .imageUrl(postDto.getImageUrl())
                        .postUrl(postDto.getPostUrl())
                        .build())
                .collect(Collectors.toList());
    }

    private PostDtos createMockPostDtosByStartIndex(int startIndex) {
        List<PostDto> postDtos = new ArrayList<>();
        for (int i = startIndex; i < startIndex + PostDtos.POPULAR_POST_SIZE; i++) {
            String dummy = String.valueOf(i);
            postDtos.add(new PostDto(dummy, dummy));
        }
        return new PostDtos(postDtos);
    }
}
