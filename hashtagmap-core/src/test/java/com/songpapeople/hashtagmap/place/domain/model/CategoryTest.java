package com.songpapeople.hashtagmap.place.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CategoryTest {

    @DisplayName("GroupCode로 Category 찾아오기 테스트")
    @Test
    void fromCategoryGroupCode() {
        assertThat(Category.fromCategoryGroupCode("CE7")).isEqualTo(Category.CAFE);
    }

    @DisplayName("존재하지 않는 GroupCode 찾을 시 예외 발생 테스트")
    @Test
    void fromCategoryGroupCodeException() {
        assertThatThrownBy(() -> Category.fromCategoryGroupCode("HAHA"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하는 않는 카테고리 코드입니다.");
    }
}