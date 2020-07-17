package com.songpapeople.hashtagmap.crawler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class JsonExplorerTest {
    @Test
    void findByKey() {
        String json = "{\"node\":{\"name\":\"hs\",\"password\":\"1234\"},\"type\":\"string\"}";
        JsonElement jsonElement = JsonParser.parseString(json);

        assertThat(JsonExplorer.findByKey(jsonElement, "name")).isEqualTo("hs");
    }
}
