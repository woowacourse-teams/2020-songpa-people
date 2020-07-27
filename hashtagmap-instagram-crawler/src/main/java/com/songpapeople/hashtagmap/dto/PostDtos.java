package com.songpapeople.hashtagmap.dto;

import com.songpapeople.hashtagmap.exception.CrawlerException;
import com.songpapeople.hashtagmap.exception.CrawlerExceptionStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostDtos {
    public static final int POPULAR_POST_SIZE = 9;

    private final List<PostDto> postDtos;

    public PostDtos(List<PostDto> postDtos) {
        if (postDtos.size() != POPULAR_POST_SIZE) {
            throw new CrawlerException(CrawlerExceptionStatus.NOT_ENOUGH_POPULAR_POST);
        }
        this.postDtos = new ArrayList<>(postDtos);
    }

    public int size() {
        return this.postDtos.size();
    }
}
