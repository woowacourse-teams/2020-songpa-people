package com.songpapeople.hashtagmap.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class PostDtos {
    public static final int POPULAR_POST_SIZE = 9;

    private final List<PostDto> postDtos;

    public PostDtos(List<PostDto> postDtos) {
        if (postDtos.size() != POPULAR_POST_SIZE) {
            throw new IllegalArgumentException("포스트가 9개가 아닙니다.");
        }
        this.postDtos = new ArrayList<>(postDtos);
    }

    public int size() {
        return this.postDtos.size();
    }
}
