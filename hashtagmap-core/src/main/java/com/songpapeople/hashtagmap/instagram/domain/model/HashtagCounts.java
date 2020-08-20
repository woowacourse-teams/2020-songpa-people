package com.songpapeople.hashtagmap.instagram.domain.model;

import java.util.List;

public class HashtagCounts {
    private final List<Long> hashtagCounts;

    public HashtagCounts(List<Long> hashtagCounts) {
        this.hashtagCounts = hashtagCounts;
    }

    public Long get(int index) {
        return hashtagCounts.get(index);
    }

    public int getSize() {
        return hashtagCounts.size();
    }

    public int getLastIndex() {
        return hashtagCounts.size() - 1;
    }
}
