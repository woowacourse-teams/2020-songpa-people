package com.songpapeople.hashtagmap.scheduler.domain.factory;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlaceFactory {
    public static List<Place> from(Set<Document> documents) {
        return documents.stream()
                .map(PlaceFactory::from)
                .collect(Collectors.toList());
    }

    public static Place from(Document document) {
        Location location = new Location(new Point(document.getLatitude(), document.getLongitude()), document.getRoadAddressName());

        return Place.builder()
                .kakaoId(document.getId())
                .placeName(document.getPlaceName())
                .category(Category.fromCategoryGroupCode(document.getCategoryGroupCode()))
                .location(location)
                .placeUrl(document.getPlaceUrl())
                .build();
    }
}
