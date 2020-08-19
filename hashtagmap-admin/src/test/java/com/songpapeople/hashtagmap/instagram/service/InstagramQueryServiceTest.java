package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.SubBlackListDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class InstagramQueryServiceTest
{
    @Autowired
    private InstagramQueryService instagramQueryService;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("hashtag수가 많은 인스타그램에서 place관련 정보를 가져오는 기능 테스트")
    @Test
    void findBlackListCandidate() {
        Place place = Place.builder()
                .placeName("place")
                .location(new Location(new Point("40", "130"), "address"))
                .build();
        List<Instagram> instagrams = LongStream.rangeClosed(1000, 1020)
                .boxed()
                .map(count -> Instagram.builder()
                        .place(place)
                        .hashtagName(place.getPlaceName())
                        .hashtagCount(count)
                        .build())
                .collect(Collectors.toList());

        placeRepository.save(place);
        instagramRepository.saveAll(instagrams);

        List<SubBlackListDto> subBlackListInstagram = instagramQueryService.findSubBlackListInstagram();
        List<Long> hashtagCounts = subBlackListInstagram.stream()
                .map(SubBlackListDto::getHashtagCount)
                .collect(Collectors.toList());

        assertAll(
                () -> assertThat(hashtagCounts).hasSize(10),
                () -> assertThat(hashtagCounts.contains(1020L)).isTrue(),
                () -> assertThat(hashtagCounts.contains(1000L)).isFalse()
        );
    }
}
