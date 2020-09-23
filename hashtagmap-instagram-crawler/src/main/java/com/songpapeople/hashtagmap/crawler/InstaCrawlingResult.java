package com.songpapeople.hashtagmap.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class InstaCrawlingResult {
    public static final String POST_URL_KEY = "shortcode";
    public static final String DISPLAY_URL_KEY = "display_url";
    public static final String SOURCE_KEY = "edges";

    private final String body;

    public String findHashTagCount() {
        return RegexPattern.HASH_TAG_COUNT.extract(body);
    }

    public PostDtos findPostDtos() {
        List<PostDto> postDtos = new ArrayList<>();
        String popularPostsInfo = RegexPattern.HASHTAG_POPULAR_POSTS_INFO.extract(body);
        JsonElement popularPostsJson = JsonParser.parseString(popularPostsInfo);
        JsonArray sources = popularPostsJson.getAsJsonObject().get(SOURCE_KEY).getAsJsonArray();
        for (JsonElement source : sources) {
            String postUrl = JsonExplorer.findByKey(source, POST_URL_KEY);
            String displayUrl = JsonExplorer.findByKey(source, DISPLAY_URL_KEY);
            postDtos.add(new PostDto(postUrl, displayUrl));
        }
        return new PostDtos(postDtos);
    }
}
