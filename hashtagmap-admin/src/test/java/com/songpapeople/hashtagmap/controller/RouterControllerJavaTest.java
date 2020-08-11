package com.songpapeople.hashtagmap.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RouterControllerJavaTest {

    @DisplayName("errorPath 반환")
    @Test
    void getErrorPathTest() {
        RouterController routerController = new RouterController();

        assertThat(routerController.getErrorPath()).isEqualTo("/error");
    }
}