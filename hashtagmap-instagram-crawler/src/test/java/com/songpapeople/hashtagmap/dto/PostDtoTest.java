package com.songpapeople.hashtagmap.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostDtoTest {
    public static final String POST_URL_FORMAT = "https://www.instagram.com/p/%s";

    @Test
    void create() {
        String shortCode = "code";
        String imageUrl = "http://www.address";

        PostDto postDto = new PostDto(shortCode, imageUrl);

        assertThat(postDto.getPostUrl()).isEqualTo("https://www.instagram.com/p/code");
    }
}
