package com.songpapeople.hashtagmap.taglevel.model;

import com.songpapeople.hashtagmap.exception.CoreException;
import com.songpapeople.hashtagmap.exception.CoreExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TagLevelTest {

    @DisplayName("해시태그 최소/최대 개수를 검증한다.")
    @Test
    public void validateHashtgaCountTest() {
        CoreException exception = assertThrows(CoreException.class,
                () -> new TagLevel(0L, 10L, 1L));
        assertEquals(exception.getErrorCode(), CoreExceptionStatus.INVALID_TAG_LEVEL.getCode());
    }
}