package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramQueryRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InstagramCrawlingServiceTest {
    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramQueryRepository instagramQueryRepository;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private InstagramCrawlingService instagramCrawlingService;

    @MockBean
    private InstagramCrawler instagramCrawler;

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

        instagramCrawlingService.updateInstagramByBlackList(oldInstagram.getKakaoId(), newHashtagName);

        Instagram newInstagram = instagramQueryRepository.findByIdFetch(oldInstagram.getId());
        List<String> newPostImageUrls = instagramPostRepository.findAllByInstagramId(oldInstagram.getId()).stream()
                .map(InstagramPost::getImageUrl)
                .collect(Collectors.toList());
        assertAll(
                () -> assertThat(newInstagram.getKakaoId()).isEqualTo(place.getKakaoId()),
                () -> assertThat(newInstagram.getHashtagName()).isEqualTo(newHashtagName),
                () -> assertThat(newInstagram.getHashtagCount()).isEqualTo(Long.parseLong(newHashtagCount)),
                () -> assertThat(newPostImageUrls.contains("100")).isTrue(),
                () -> assertThat(newPostImageUrls.contains("1")).isFalse()
        );
    }

    @DisplayName("Skip하는 가게라면 크롤링하지 않고 빈 결과를 반환한다.")
    @Test
    void isSkipTest() {
        Place place = placeRepository.save(
                Place.builder()
                        .kakaoId("12345")
                        .build()
        );
        blackListRepository.save(new BlackList(place.getKakaoId(), "newName", true));

        Optional<CrawlingResult> crawlingResult = instagramCrawlingService.createCrawlingResult(place);
        assertThat(crawlingResult.isPresent()).isFalse();
    }

    @DisplayName("blackList에 등록되지 않은 경우 검색어 테스트")
    @Test
    void findHashtagNameToCrawlTest() {
        // given
        final String PLACE_NAME = "스타벅스잠실";

        Place place = placeRepository.save(
                Place.builder()
                        .placeName(PLACE_NAME)
                        .kakaoId("12345")
                        .build()
        );

        CrawlingDto crawlingDto = CrawlingDto.of(PLACE_NAME, String.valueOf(1000), null);
        when(instagramCrawler.crawler(PLACE_NAME)).thenReturn(crawlingDto);

        // when
        Optional<CrawlingResult> crawlingResult = instagramCrawlingService.createCrawlingResult(place);

        // then
        String hashtagName = crawlingResult.get().createInstagram().getHashtagName();
        assertThat(hashtagName).isEqualTo(PLACE_NAME);
    }

    @DisplayName("blackList에 등록된 경우 검색어 테스트")
    @Test
    void findBlackListHashtagNameToCrawlTest() {
        // given
        final String PLACE_NAME = "블루보틀성수역점";
        final String NEW_HASHTAG_NAME = "블루보틀";

        Place place = placeRepository.save(
                Place.builder()
                        .placeName(PLACE_NAME)
                        .kakaoId("12345")
                        .build()
        );
        blackListRepository.save(new BlackList(place.getKakaoId(), NEW_HASHTAG_NAME));

        CrawlingDto crawlingDto = CrawlingDto.of(NEW_HASHTAG_NAME, String.valueOf(1000), null);
        when(instagramCrawler.crawler(NEW_HASHTAG_NAME)).thenReturn(crawlingDto);

        // when
        Optional<CrawlingResult> crawlingResult = instagramCrawlingService.createCrawlingResult(place);

        // then
        String hashtagName = crawlingResult.get().createInstagram().getHashtagName();
        assertThat(hashtagName).isEqualTo(NEW_HASHTAG_NAME);
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

    @AfterEach
    private void tearDown() {
        instagramPostRepository.deleteAll();
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
        blackListRepository.deleteAll();
    }
}
