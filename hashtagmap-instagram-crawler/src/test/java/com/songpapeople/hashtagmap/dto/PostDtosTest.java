package com.songpapeople.hashtagmap.dto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostDtosTest {
    private List<PostDto> postDtos;

    @BeforeEach
    void setUp() {
        postDtos = new ArrayList<>();
        for (int i = 1; i <= PostDtos.POPULAR_POST_SIZE; i++) {
            String dummy = String.valueOf(i);
            postDtos.add(new PostDto(dummy, dummy));
        }
    }

    @DisplayName("PostDto가 9개인 경우 정상 동작 테스트")
    @Test
    void create() {
        assertThat(new PostDtos(postDtos)).isNotNull();
    }

    @DisplayName("PostDto가 10개인 경우 Exception 테스트")
    @Test
    void createOverSize() {
        postDtos.add(new PostDto("a", "a"));
        assertThatThrownBy(() -> new PostDtos(postDtos)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("PostDto가 8개인 경우 Exception 테스트")
    @Test
    void createUnderSize() {
        postDtos.remove(0);
        assertThatThrownBy(() -> new PostDtos(postDtos)).isInstanceOf(IllegalArgumentException.class);
    }
}
