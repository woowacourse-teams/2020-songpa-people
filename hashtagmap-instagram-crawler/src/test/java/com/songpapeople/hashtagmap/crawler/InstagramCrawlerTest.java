package com.songpapeople.hashtagmap.crawler;

import org.junit.jupiter.api.Test;

class InstagramCrawlerTest {

    @Test
    void createHashtagDto() {
        InstagramCrawler instagramCrawler = new InstagramCrawler();
        System.out.println(instagramCrawler.createHashtagDto("스타벅스"));


    }
}
