package com.songpapeople.hashtagmap.instagram.domain.model.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstagramForUpdate {
    private Long id;
    private Long placeId;

    public Instagram toInstagram() {
        return Instagram.builder()
                .id(id)
                .place(Place.builder().id(placeId).build())
                .build();
    }
}
