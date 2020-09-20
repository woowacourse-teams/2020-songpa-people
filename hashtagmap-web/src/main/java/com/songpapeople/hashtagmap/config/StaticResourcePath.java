package com.songpapeople.hashtagmap.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum StaticResourcePath {
    JS("/js/**", "js"),
    CSS("/css/**", "css"),
    IMG("/img/**", "img");

    private final String path;
    private final String directory;

}
