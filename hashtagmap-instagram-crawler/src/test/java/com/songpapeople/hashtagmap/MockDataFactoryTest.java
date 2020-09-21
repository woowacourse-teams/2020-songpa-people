package com.songpapeople.hashtagmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class MockDataFactoryTest {

    @DisplayName("2020-9월 기준 insta 크롤링 성공 mock 데이터")
    @Test
    void createBody() throws IOException {
        assertThat(MockDataFactory.createBody()).isNotBlank();
    }
}