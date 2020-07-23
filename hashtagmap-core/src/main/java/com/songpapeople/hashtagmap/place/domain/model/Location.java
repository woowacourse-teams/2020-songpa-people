package com.songpapeople.hashtagmap.place.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {
    @Embedded
    private Point point;

    private String roadAddressName;
}
