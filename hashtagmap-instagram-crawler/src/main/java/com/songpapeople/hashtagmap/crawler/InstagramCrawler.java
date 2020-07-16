package com.songpapeople.hashtagmap.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class InstagramCrawler {
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";
    private static final String HASHTAG_COUNT_REGEX = "(\"edge_hashtag_to_media\":\\{\"count\"):([0-9]+)";
    private static final String HASHTAG_POPULAR_POSTS_INFO_REGEX = "(\"edge_hashtag_to_top_posts\":)(.*)(,\"edge_hashtag_to_content_advisory\")";
    public static final String POST_URL_KEY = "shortcode";
    public static final String DISPLAY_URL_KEY = "display_url";
    public static final String SOURCE_KEY = "edges";

    public CrawlingDto createHashtagDto(String placeName) {
        Document document = Crawler.crawling(String.format(INSTAGRAM_URL_FORMAT, placeName));
        String body = document.body().toString();
        String hashtagCount = Extractor.extractByRegex(body, HASHTAG_COUNT_REGEX);
        String popularPostsInfo = Extractor.extractByRegex(body, HASHTAG_POPULAR_POSTS_INFO_REGEX);
        PostDtos postDtos = makePostDtos(popularPostsInfo);
        return CrawlingDto.of(placeName, hashtagCount, postDtos);
    }

    private PostDtos makePostDtos(String popularPostsInfo) {
        List<PostDto> postDtos = new ArrayList<>();
        JsonElement popularPostsJson = JsonParser.parseString(popularPostsInfo);
        JsonArray sources = popularPostsJson.getAsJsonObject().get(SOURCE_KEY).getAsJsonArray();
        for (JsonElement source : sources) {
            String postUrl = Extractor.extractByKey(source, POST_URL_KEY);
            String displayUrl = Extractor.extractByKey(source, DISPLAY_URL_KEY);
            postDtos.add(new PostDto(postUrl, displayUrl));
        }
        return new PostDtos(postDtos);
    }
}
