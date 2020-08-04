package com.songpapeople.hashtagmap.config.vo;

public enum Flag {
    Y(true),
    N(false);

    private final boolean flag;

    Flag(final boolean flag) {
        this.flag = flag;
    }

    public boolean isYes() {
        return flag;
    }

    public Flag toggle() {
        if (this == Y) {
            return N;
        }
        return Y;
    }
}
