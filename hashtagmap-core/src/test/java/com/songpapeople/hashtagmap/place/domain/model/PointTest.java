package com.songpapeople.hashtagmap.place.domain.model;

import com.songpapeople.hashtagmap.exception.CoreException;
import com.songpapeople.hashtagmap.exception.CoreExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointTest {

    @DisplayName("위도 범위를 검증한다.")
    @ParameterizedTest
    @ValueSource(strings = {"32.5", "43.1"})
    public void validateLatitudeTest(String invalidLatitude) {
        CoreException exception = assertThrows(CoreException.class,
                () -> new Point(invalidLatitude, "124"));
        assertEquals(exception.getErrorCode(), CoreExceptionStatus.INVALID_LATITUDE);
    }

    @DisplayName("경도 범위를 검증한다.")
    @ParameterizedTest
    @ValueSource(strings = {"123.9", "132.1"})
    public void validateLongitudeTest(String invalidLongitude) {
        CoreException exception = assertThrows(CoreException.class,
                () -> new Point("34", invalidLongitude));
        assertEquals(exception.getErrorCode(), CoreExceptionStatus.INVALID_LONGITUDE);
    }
}