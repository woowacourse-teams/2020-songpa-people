package com.songpapeople.hashtagmap.place.domain.model;

public enum Category {
    CAFE("CE7", "카페"),
    RESTAURANT("FD6", "음식점");

    private String categoryGroupCode;
    private String categoryGroupName;

    Category(String categoryGroupCode, String categoryGroupName) {
        this.categoryGroupCode = categoryGroupCode;
        this.categoryGroupName = categoryGroupName;
    }
}
