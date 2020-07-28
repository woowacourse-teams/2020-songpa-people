package com.songpapeople.hashtagmap.place.domain.model;

import java.util.Arrays;

public enum Category {
    CAFE("CE7", "카페"),
    RESTAURANT("FD6", "음식점");

    private String categoryGroupCode;
    private String categoryGroupName;

    Category(String categoryGroupCode, String categoryGroupName) {
        this.categoryGroupCode = categoryGroupCode;
        this.categoryGroupName = categoryGroupName;
    }

    public String getCategoryGroupCode() {
        return categoryGroupCode;
    }

    public static Category fromCategoryGroupCode(String code) {
        return Arrays.stream(Category.values())
                .filter(category -> category.categoryGroupCode.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 않는 카테고리 코드입니다."));
    }
}
