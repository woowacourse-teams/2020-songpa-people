package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.MockDataFactory;
import com.songpapeople.hashtagmap.dto.PostDtos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InstaCrawlingResultTest {
    @DisplayName("body에서 인기게시물 정보를 가져와 PostDtos를 만드는 기능 테스트")
    @Test
    void findPostDtos() {
        String body = MockDataFactory.createBody();
        InstaCrawlingResult instaCrawlingResult = new InstaCrawlingResult(body);

        PostDtos postDtos = instaCrawlingResult.findPostDtos();

        assertThat(postDtos.size()).isEqualTo(PostDtos.POPULAR_POST_SIZE);
    }

    @DisplayName("body에서 hashtagCount를 찾는 테스트")
    @Test
    void findHashTagCount() {
        String body = MockDataFactory.createBody();
        InstaCrawlingResult instaCrawlingResult = new InstaCrawlingResult(body);

        String hashTagCount = instaCrawlingResult.findHashTagCount();

        assertThat(hashTagCount).isNotNull();
    }
}
