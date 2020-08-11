package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.InstagramPost.InstagramPostResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InstagramPostQueryServiceTest {
    @Autowired
    private InstagramPostQueryService instagramPostQueryService;

    @MockBean
    private InstagramPostRepository instagramPostRepository;

    @DisplayName("인스타 아이디로부터 모든 post 정보를 가져오는 기능 테스트")
    @Test
    void findAllByInstagramId() {
        List<InstagramPost> expected = IntStream.rangeClosed(1, 9).boxed()
                .map(this::createInstagramPost)
                .collect(Collectors.toList());

        when(instagramPostRepository.findAllByInstagramId(any())).thenReturn(expected);

        List<InstagramPostResponse> actual = instagramPostQueryService.findAllByInstagramId(1L);

        assertThat(actual).isEqualTo(expected);
    }

    private InstagramPost createInstagramPost(Integer number) {
        String url = String.valueOf(number);
        return InstagramPost.builder()
                .instagram(Instagram.builder().id(1L).build())
                .imageUrl(url)
                .postUrl(url)
                .build();
    }
}
