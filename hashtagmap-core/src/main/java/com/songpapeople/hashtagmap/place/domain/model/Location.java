package com.songpapeople.hashtagmap.place.domain.model;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
public class Location {
    @Embedded
    private Point point;

    private String roadAddressName;
}
