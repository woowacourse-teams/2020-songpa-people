package com.songpapeople.hashtagmap.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostDtoTest {
    @DisplayName("PostDto 생성시 받아온 url key값으로 postUrl을 정상적으로 생성하는지 테스트")
    @Test
    void create() {
        String shortCode = "code";
        String imageUrl = "http://www.address";

        PostDto postDto = new PostDto(shortCode, imageUrl);

        assertThat(postDto.getPostUrl()).isEqualTo("https://www.instagram.com/p/code");
    }
}
