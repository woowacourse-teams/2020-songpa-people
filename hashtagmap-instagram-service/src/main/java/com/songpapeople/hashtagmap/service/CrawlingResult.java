package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerException;
import com.songpapeople.hashtagmap.exception.InstagramSchedulerExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CrawlingResult {
    public static final long MIN_HASHTAG_COUNT = 500;

    private CrawlingDto crawlingDto;
    private Place place;

    public CrawlingResult(CrawlingDto crawlingDto, Place place) {
        validateHashtagCount(crawlingDto);
        this.crawlingDto = crawlingDto;
        this.place = place;
    }

    private void validateHashtagCount(CrawlingDto crawlingDto) {
        if (crawlingDto.getHashtagCount() < MIN_HASHTAG_COUNT) {
            throw new InstagramSchedulerException(InstagramSchedulerExceptionStatus.NOT_ENOUGH_HASHTAG_COUNT);
        }
    }

    public List<InstagramPost> toInstagramPosts(Instagram instagram) {
        List<PostDto> postDtoList = crawlingDto.getPostDtoList();
        return postDtoList.stream()
                .map(postDto -> createInstagramPost(instagram, postDto))
                .collect(Collectors.toList());
    }

    public Instagram createInstagram() {
        return Instagram.builder()
                .place(place)
                .hashtagName(crawlingDto.getHashtagName())
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

    public Place getPlace() {
        return place;
    }
}
