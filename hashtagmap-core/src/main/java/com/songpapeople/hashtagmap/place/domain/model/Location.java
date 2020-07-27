package com.songpapeople.hashtagmap.place.domain.model;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {
    @Embedded
    private Point point;

    private String roadAddressName;

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(point, location.point)
                && Objects.equals(roadAddressName, location.roadAddressName);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(point, roadAddressName);
    }
}
