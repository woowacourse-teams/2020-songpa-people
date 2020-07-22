package com.songpapeople.hashtagmap.place.domain.model;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Point {
    private String latitude;
    private String longitude;
}
