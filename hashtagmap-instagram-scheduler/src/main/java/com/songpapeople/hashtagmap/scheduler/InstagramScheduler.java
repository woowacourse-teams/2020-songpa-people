package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.proxy.Proxy;

import java.util.ArrayList;
import java.util.List;

public class InstagramScheduler {

    public CrawlingDto crawlingWithProxy(Proxy proxy, Place place) {

        List<PostDto> postDtos = new ArrayList<>();
        postDtos.add(new PostDto("a", "a"));
        postDtos.add(new PostDto("a", "a"));
        postDtos.add(new PostDto("a", "a"));
        postDtos.add(new PostDto("a", "a"));
        postDtos.add(new PostDto("a", "a"));
        postDtos.add(new PostDto("a", "a"));
        postDtos.add(new PostDto("a", "a"));
        postDtos.add(new PostDto("a", "a"));
        postDtos.add(new PostDto("a", "a"));
        return CrawlingDto.of("스타벅스", "10", new PostDtos(postDtos));
    }
}
