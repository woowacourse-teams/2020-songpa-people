package com.songpapeople.hashtagmap.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;

public class InstagramCrawler {
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";
    private static final String HASHTAG_COUNT_REGEX = "(\"edge_hashtag_to_media\":\\{\"count\"):([0-9]+)";
    private static final String HASHTAG_POPULAR_INFO_REGEX = "(\"edge_hashtag_to_top_posts\":)(.*)(,\"edge_hashtag_to_content_advisory\")";

    public CrawlingDto createHashtagDto(String placeName) {
        Document document = Crawler.crawling(String.format(INSTAGRAM_URL_FORMAT, placeName));
        String body = document.body().toString();
        String hashtagCount = CrawlingProcessor.searchByRegex(body, HASHTAG_COUNT_REGEX);
        String popularInfo = CrawlingProcessor.searchByRegex(body, HASHTAG_POPULAR_INFO_REGEX);
        List<PostDto> postDtos = makePostDtos(popularInfo);

        return CrawlingDto.of(placeName, hashtagCount, postDtos);
    }

    private List<PostDto> makePostDtos(String popularInfo) {
        List<PostDto> postDtos = new ArrayList<>();
        JsonElement json = JsonParser.parseString(popularInfo);
        JsonArray edges = json.getAsJsonObject().get("edges").getAsJsonArray();
        for (JsonElement edge : edges) {
            JsonObject node = edge.getAsJsonObject().get("node").getAsJsonObject();

            String shortCode = node.get("shortcode").toString();
            String displayUrl = node.get("display_url").toString();
            postDtos.add(new PostDto(shortCode, displayUrl));
        }
        return postDtos;
    }
}
