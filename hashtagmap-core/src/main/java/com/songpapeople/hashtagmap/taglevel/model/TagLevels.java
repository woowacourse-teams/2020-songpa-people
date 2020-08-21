package com.songpapeople.hashtagmap.taglevel.model;

import java.util.List;

public class TagLevels {
    private final List<TagLevel> tagLevels;

    public TagLevels(List<TagLevel> tagLevels) {
        this.tagLevels = tagLevels;
    }

    public int getSize() {
        return this.tagLevels.size();
    }

    public void update(int index, Long minHashtagCount, Long maxHashtagCount) {
        tagLevels.get(index).updateMinHashtagCount(minHashtagCount);
        tagLevels.get(index).updateMaxHashtagCount(maxHashtagCount);
    }

    public int findIndex(TagLevel tagLevel) {
        return tagLevels.indexOf(tagLevel);
    }

    public boolean isLastIndex(int index) {
        return (tagLevels.size() - 1) == index;
    }

    public TagLevel get(int index) {
        return tagLevels.get(index);
    }
}
