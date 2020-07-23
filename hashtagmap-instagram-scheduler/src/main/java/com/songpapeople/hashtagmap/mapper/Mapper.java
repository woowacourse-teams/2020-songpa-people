package com.songpapeople.hashtagmap.mapper;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.place.domain.model.Place;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Instagram toInstagram(CrawlingDto crawlingDto, Place place) {
        return Instagram.builder()
                .hashtagName(crawlingDto.getPlaceName())
                .hashtagCount(crawlingDto.getHashtagCount())
                .place(place)
                .build();
    }

    public static List<InstagramPost> toInstagramPosts(PostDtos postDtos, Instagram instagram) {
        List<InstagramPost> instagramPosts = new ArrayList<>();
        for (PostDto postDto : postDtos.getPostDtos()) {
            instagramPosts.add(toInstagramPost(postDto, instagram));
        }
        return instagramPosts;
    }

    public static InstagramPost toInstagramPost(PostDto postDto, Instagram instagram) {
        return InstagramPost.builder()
                .imageUrl(postDto.getImageUrl())
                .postUrl(postDto.getPostUrl())
                .instagram(instagram)
                .build();
    }
}
