package com.songpapeople.hashtagmap.place.domain.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class Point {
    private String latitude;
    private String longitude;

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(latitude, point.latitude)
                && Objects.equals(longitude, point.longitude);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
