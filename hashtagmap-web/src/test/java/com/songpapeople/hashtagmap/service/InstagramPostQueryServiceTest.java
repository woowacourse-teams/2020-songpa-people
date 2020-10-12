package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.InstagramPostResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InstagramPostQueryServiceTest {
    @Autowired
    private InstagramPostQueryService instagramPostQueryService;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @DisplayName("인스타 아이디로부터 모든 post 정보를 가져오는 기능 테스트")
    @Test
    void findAllByInstagramId() {
        Instagram instagram = instagramRepository.save(Instagram.builder().build());
        List<InstagramPost> instagramPosts = IntStream.rangeClosed(1, 9).boxed()
                .map(count -> createInstagramPost(count, instagram))
                .collect(Collectors.toList());
        instagramPostRepository.saveAll(instagramPosts);

        List<InstagramPostResponse> expected = instagramPosts.stream()
                .map(InstagramPostResponse::of)
                .collect(Collectors.toList());

        List<InstagramPostResponse> actual = instagramPostQueryService.findAllByInstagramId(instagram.getId());

        assertThat(actual).isEqualTo(expected);
    }

    private InstagramPost createInstagramPost(Integer count, Instagram instagram) {
        String url = String.valueOf(count);
        return InstagramPost.builder()
                .instagram(instagram)
                .imageUrl(url)
                .postUrl(url)
                .build();
    }
}
