package com.songpapeople.hashtagmap.crawler;

import com.songpapeople.hashtagmap.dto.PostDtos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class InstaCrawlingResultTest {
    @DisplayName("body에서 인기게시물 정보를 가져와 PostDtos를 만드는 기능 테스트")
    @Test
    void findPostDtos() {
        String body = createbody();
        InstaCrawlingResult instaCrawlingResult = new InstaCrawlingResult(body);

        PostDtos postDtos = instaCrawlingResult.findPostDtos();

        assertThat(postDtos.size()).isEqualTo(PostDtos.POPULAR_POST_SIZE);
    }

    String createbody() {
        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(new File("src/test/resources/crawling-mock-data.txt"))) {
            while (true) {
                int fileData = fileReader.read();
                if (fileData == -1) {
                    break;
                }
                builder.append((char) fileData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
