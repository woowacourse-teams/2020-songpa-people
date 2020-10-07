package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

@SpringBootTest
class BlackListCommandServiceTest {
    @Autowired
    private BlackListCommandService blackListCommandService;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @MockBean
    private InstagramCrawler instagramCrawler;

    @DisplayName("잘못된 검색어로 검색된 가게 삭제후 블랙리스트 등록 기능 테스트")
    @Test
    void deleteInstagramAfterAddBlackList() {
        Place place = Place.builder()
                .placeName("place")
                .location(new Location(new Point("40", "130"), "address"))
                .kakaoId("351421")
                .build();
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagCount(100L)
                .hashtagName(place.getPlaceName())
                .build();
        placeRepository.save(place);
        instagramRepository.save(instagram);

        BlackListRequest blackListRequest = new BlackListRequest(place.getKakaoId(), "");
        blackListCommandService.deleteInstagramAfterAddBlackList(blackListRequest);

        assertAll(
                () -> assertThat(blackListRepository.findByKakaoId(place.getKakaoId()).isPresent()).isTrue(),
                () -> assertThat(instagramRepository.findById(instagram.getId()).isPresent()).isFalse()
        );
    }

    @DisplayName("blackList 등록하는 과정에서 Instagram과 post를 대체어로 검색한결과로 업데이트시키는 기능 테스트")
    @Test
    void updateBlackLists() {
        // given
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

        // when
        final String newHashtagName = "newHashtagName";
        final String newHashtagCount = "1000";
        PostDtos newPostDtos = createMockPostDtosByStartIndex(100);
        CrawlingDto crawlingDto = CrawlingDto.of(newHashtagName, newHashtagCount, newPostDtos);
        when(instagramCrawler.crawler(any())).thenReturn(crawlingDto);

        Instagram newInstagram = blackListCommandService.updateInstagramByBlackList(oldInstagram.getKakaoId(), newHashtagName);

        List<String> newPostImageUrls = instagramPostRepository.findAllByInstagramId(oldInstagram.getId()).stream()
                .map(InstagramPost::getImageUrl)
                .collect(Collectors.toList());
        assertAll(
                () -> assertThat(newInstagram.getHashtagName()).isEqualTo(newHashtagName),
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
