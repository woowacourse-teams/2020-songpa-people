package com.songpapeople.hashtagmap.scheduler.domain.factory;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceFactoryTest {
    @DisplayName("Document를 Place로 변환한다")
    @Test
    public void documentToPlaceTest() {
        Document document = Document.builder()
                .id("16618597")
                .placeName("비엔나 커피")
                .categoryGroupCode("CE7")
                .roadAddressName("서울 강남구 테헤란로84길 17")
                .latitude("37.506051888130386")
                .longitude("127.05897078335246")
                .placeUrl("http://place.map.kakao.com/16618597")
                .build();
        Location location = new Location(new Point(document.getLatitude(), document.getLongitude()), document.getRoadAddressName());
        Place expected = Place.builder()
                .kakaoId(document.getId())
                .placeName(document.getPlaceName())
                .category(Category.fromCategoryGroupCode(document.getCategoryGroupCode()))
                .location(location)
                .placeUrl(document.getPlaceUrl())
                .build();

        Place actual = PlaceFactory.from(document);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}
