package com.songpapeople.hashtagmap.place.domain.model;

import lombok.*;

import javax.persistence.Embeddable;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class Point {
    private String latitude;
    private String longitude;
}
