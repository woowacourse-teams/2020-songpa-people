package com.songpapeople.hashtagmap.crawler;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;

class CrawlingProcessorTest {

    @Test
    void searchByRegex() {
        String body = "<div>hello-world</div>";
        String regex = "(\\<div\\>)(.*)(\\</div\\>)";

        assertThat(CrawlingProcessor.searchByRegex(body, regex)).isEqualTo("hello-world");
    }
}