package com.songpapeople.hashtagmap.crawler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonExplorerTest {
    @DisplayName("json 데이터에서 key값으로 value값을 가져오는 기능 테스트")
    @Test
    void findByKey() {
        String json = "{\"node\":{\"name\":\"hs\",\"password\":\"1234\"},\"type\":\"string\"}";
        JsonElement jsonElement = JsonParser.parseString(json);

        assertThat(JsonExplorer.findByKey(jsonElement, "name")).isEqualTo("hs");
    }
}
