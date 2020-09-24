package com.songpapeople.hashtagmap.config;

import com.songpapeople.hashtagmap.crawler.Crawler;
import com.songpapeople.hashtagmap.crawler.InstagramCrawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstagramSchedulerConfiguration {
    @Bean
    public InstagramCrawler instagramCrawler() {
        return new InstagramCrawler(new Crawler());
    }
}
