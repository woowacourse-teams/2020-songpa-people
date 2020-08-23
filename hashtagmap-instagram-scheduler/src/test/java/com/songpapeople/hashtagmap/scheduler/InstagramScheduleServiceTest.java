package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
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

    @Autowired
    private BlackListRepository blackListRepository;

    @MockBean
    private InstagramCrawler instagramCrawler;

    @DisplayName("blackList등록하는 과정에서 Instagram과 post를 대체어로 검색한결과로 업데이트시키는 기능 테스트")
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

    @DisplayName("place가 blackList면 대체어를, 아니라면 place의 이름을 리턴하는 메서드 테스트 - 블랙리스트일때")
    @Test
    void findHashtagNameToCrawWhenBlackList() {
        Place place = Place.builder()
                .placeName("orginName")
                .build();
        placeRepository.save(place);

        BlackList blackList = new BlackList(place.getId(),"replaceName");
        blackListRepository.save(blackList);

        String hashtagNameToCraw = instagramScheduleService.findHashtagNameToCraw(place);

        assertThat(hashtagNameToCraw).isEqualTo(blackList.getReplaceName());
    }

    @DisplayName("place가 blackList면 대체어를, 아니라면 place의 이름을 리턴하는 메서드 테스트 - 블랙리스트가 아닐때")
    @Test
    void findHashtagNameToCrawWhenNotBlackList() {
        Place place = Place.builder()
                .placeName("orginName")
                .build();
        placeRepository.save(place);

        String hashtagNameToCraw = instagramScheduleService.findHashtagNameToCraw(place);

        assertThat(hashtagNameToCraw).isEqualTo(place.getPlaceName());
    }

    @DisplayName("크롤링을 스킵하는 place인지 리턴하는 메서드 테스트 - true")
    @Test
    void isSkipPlaceWhenTrue() {
        Place skipPlace = Place.builder()
                .placeName("skipPlace")
                .build();
        placeRepository.save(skipPlace);

        BlackList blackList = new BlackList(skipPlace.getId(),"");
        blackList.setSkipPlace(true);
        blackListRepository.save(blackList);

        assertThat(instagramScheduleService.isSkipPlace(skipPlace)).isTrue();
    }

    @DisplayName("크롤링을 스킵하는 place인지 리턴하는 메서드 테스트 - false")
    @Test
    void isSkipPlaceWhenFalse() {
        Place nonSkipPlace = Place.builder()
                .placeName("nonSkipPlace")
                .build();
        placeRepository.save(nonSkipPlace);

        assertThat(instagramScheduleService.isSkipPlace(nonSkipPlace)).isFalse();
    }
}
