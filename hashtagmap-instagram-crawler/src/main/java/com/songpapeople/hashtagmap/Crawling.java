package com.songpapeople.hashtagmap;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import com.songpapeople.hashtagmap.crawler.Crawler;

public class Crawling {
    private static final String HASHTAG_COUNT_REGEX = "\"edge_hashtag_to_media\":\\{\"count\":[0-9]+";
    private static final String INSTAGRAM_URL_FORMAT = "https://www.instagram.com/explore/tags/%s/?hl=ko";
    private static final String HASHTAG_COUNT_BEFORE_STRING = "count\":";

    private final Random random;

    public Crawling(Random random) {
        this.random = random;
    }

    public static List<String> getList() {
        try (FileReader fileReader = new FileReader(new File("C:/Users/ebseu/Desktop/식당리스트.txt"))) {
            StringBuilder builder = new StringBuilder();
            while (true) {
                int fileData = fileReader.read();
                if (fileData == -1) {
                    break;
                }
                builder.append((char)fileData);
            }
            String unprocessedData = builder.toString();
            return Arrays.asList(unprocessedData.split(System.lineSeparator()));
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    public void start() {
        List<String> stores = getList();
        List<HashtagDto> HashtagDtos = new ArrayList<>();
        long now = System.currentTimeMillis();

        for (String store : stores) {
            String instaUrl = String.format(INSTAGRAM_URL_FORMAT, store);
            Document instaDoc = Crawler.crawling(instaUrl);
            Pattern pattern = Pattern.compile(HASHTAG_COUNT_REGEX);
            Matcher matcher = pattern.matcher(instaDoc.toString());
            if (matcher.find()) {
                String hashtagCount = matcher.group().split(HASHTAG_COUNT_BEFORE_STRING)[1];
                HashtagDtos.add(HashtagDto.of(store, hashtagCount));
            }
        }
        System.out.println(HashtagDtos.toString());
        System.out.println((System.currentTimeMillis() - now) / 1000);
    }
}
