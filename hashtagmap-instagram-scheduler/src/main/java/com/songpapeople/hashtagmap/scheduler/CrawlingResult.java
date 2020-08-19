package com.songpapeople.hashtagmap.scheduler;

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
    public static final int MIN_HASHTAG_COUNT = 500;

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

    public List<InstagramPost> toInstagramPosts(Long instagramId) {
        List<PostDto> postDtoList = crawlingDto.getPostDtoList();
        return postDtoList.stream()
                .map(postDto -> createInstagramPost(instagramId, postDto))
                .collect(Collectors.toList());
    }

    public Instagram createInstagram() {
        return Instagram.builder()
                .place(place)
                .hashtagName(crawlingDto.getPlaceName())
                .hashtagCount(crawlingDto.getHashtagCount())
                .build();
    }

    private InstagramPost createInstagramPost(Long instagramId, PostDto postDto) {
        return InstagramPost.builder()
                .instagramId(instagramId)
                .postUrl(postDto.getPostUrl())
                .imageUrl(postDto.getImageUrl())
                .build();
    }
}
