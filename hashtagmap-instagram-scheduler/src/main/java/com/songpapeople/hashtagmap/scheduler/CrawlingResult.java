package com.songpapeople.hashtagmap.scheduler;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CrawlingResult {
    private static final int MIN_HASHTAG_COUNT = 100;

    private CrawlingDto crawlingDto;
    private Place place;

    public CrawlingResult(CrawlingDto crawlingDto, Place place) {
        this.crawlingDto = crawlingDto;
        this.place = place;
    }

    public List<InstagramPost> toInstagramPosts() {
        Instagram instagram = createInstagram();
        List<PostDto> postDtoList = crawlingDto.getPostDtoList();
        return postDtoList.stream()
                .map(postDto -> createInstagramPost(instagram, postDto))
                .collect(Collectors.toList());
    }

    public Instagram createInstagram() {
        return Instagram.builder()
                .place(place)
                .hashtagName(crawlingDto.getPlaceName())
                .hashtagCount(crawlingDto.getHashtagCount())
                .build();
    }

    private InstagramPost createInstagramPost(Instagram instagram, PostDto postDto) {
        return InstagramPost.builder()
                .instagram(instagram)
                .postUrl(postDto.getPostUrl())
                .imageUrl(postDto.getImageUrl())
                .build();
    }

    public boolean isOverMinHashtagCount() {
        return this.crawlingDto.getHashtagCount() >= MIN_HASHTAG_COUNT;
    }
}
