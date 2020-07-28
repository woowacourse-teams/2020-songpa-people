package com.songpapeople.hashtagmap.place.domain.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
        return Objects.equals(this.getPoint(), location.getPoint())
                && Objects.equals(this.getRoadAddressName(), location.getRoadAddressName());
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(this.getPoint(), this.getRoadAddressName());
    }
}