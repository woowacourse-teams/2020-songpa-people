package com.songpapeople.hashtagmap.dto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PostDtosTest {

    @Test
    void createOverSize() {
        List<PostDto> postDtos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String dummy = String.valueOf(i);
            postDtos.add(new PostDto(dummy, dummy));
        }

        assertThatThrownBy(() -> new PostDtos(postDtos)).isInstanceOf(IllegalArgumentException.class);
    }
}
