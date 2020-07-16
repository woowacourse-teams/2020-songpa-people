package com.songpapeople.hashtagmap.crawler;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;

import com.songpapeople.hashtagmap.exception.NotFoundExtractorException;

class ExtractorTest {

    @Test
    void searchByRegex() {
        String body = "<div>hello-world</div>";
        String regex = "(\\<div\\>)(.*)(\\</div\\>)";

        assertThat(Extractor.extractByRegex(body, regex)).isEqualTo("hello-world");
    }

    @Test
    void NotFoundExtractorException() {
        String body = "<div>hello-world</div>";
        String regex = "(\\<h1\\>)(.*)(\\</h1\\>)";

        assertThatThrownBy(() -> Extractor.extractByRegex(body, regex)).isInstanceOf(NotFoundExtractorException.class);
    }
}