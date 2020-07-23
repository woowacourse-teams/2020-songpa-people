package com.songpapeople.hashtagmap.mapper;

import java.util.ArrayList;
import java.util.List;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.place.domain.model.Place;

public class Mapper {

    public static Instagram toInstagram(CrawlingDto crawlingDto, Place place) {
        return Instagram.builder()
            .hashtagName(crawlingDto.getPlaceName())
            .hashtagCount(crawlingDto.getHashtagCount())
            .place(place)
            .build();
    }

    public static List<InstagramPost> toInstagramPost(PostDtos postDtos, Instagram instagram) {
        List<InstagramPost> instagramPosts = new ArrayList<>();
        for (PostDto postDto : postDtos.getPostDtos()) {
            instagramPosts.add(InstagramPost.builder()
                .instagram(instagram)
                .imageUrl(postDto.getImageUrl())
                .postUrl(postDto.getPostUrl())
                .build());
        }
        return instagramPosts;
    }
}
