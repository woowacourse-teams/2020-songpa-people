package com.songpapeople.hashtagmap.place.domain.repository;

import com.songpapeople.hashtagmap.place.domain.model.Place;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaceRepositoryCustomImpl implements PlaceRepositoryCustom {
    private static final String SQL = "INSERT INTO place " +
            "(created_date, modified_date, category, kakao_id, latitude, longitude, road_address_name, place_name, place_url) " +
            "VALUES (:created_date, :modified_date, :category, :kakao_id, :latitude, :longitude, :road_address_name, :place_name, :place_url) " +
            "ON DUPLICATE KEY UPDATE " +
            "created_date = VALUES(created_date), " +
            "modified_date = VALUES(modified_date), " +
            "category = VALUES(category), " +
            "latitude = VALUES(latitude), " +
            "longitude = VALUES(longitude), " +
            "road_address_name = VALUES(road_address_name), " +
            "place_name = VALUES(place_name), " +
            "place_url = VALUES(place_url);";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PlaceRepositoryCustomImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void updateAndInsert(List<Place> places) {
        List<Map<String, Object>> batchValues = setParameters(places);

        namedParameterJdbcTemplate.batchUpdate(SQL, batchValues.toArray(new Map[places.size()]));
    }

    private List<Map<String, Object>> setParameters(List<Place> places) {
        List<Map<String, Object>> batchValues = new ArrayList<>(places.size());
        for (Place place : places) {
            batchValues.add(
                    new MapSqlParameterSource()
                            .addValue("created_date", LocalDateTime.now())
                            .addValue("modified_date", LocalDateTime.now())
                            .addValue("category", place.getCategory().toString())
                            .addValue("kakao_id", place.getKakaoId())
                            .addValue("latitude", place.getLocation().getPoint().getLatitude())
                            .addValue("longitude", place.getLocation().getPoint().getLongitude())
                            .addValue("road_address_name", place.getLocation().getRoadAddressName())
                            .addValue("place_name", place.getPlaceName())
                            .addValue("place_url", place.getPlaceUrl())
                            .getValues()
            );
        }

        return batchValues;
    }
}
