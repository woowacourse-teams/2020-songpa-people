package com.songpapeople.hashtagmap.job;

import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.scheduler.CrawlingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class InstagramCrawlingJobConfiguration {
    private static final int chunkSize = 2;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final InstagramBatchProcessor instagramBatchProcessor;

    @Bean
    public Job crawlingJob() {
        return jobBuilderFactory.get("crawlingJob")
                .start(crawlingStep())
                .build();
    }

    @Bean
    public Step crawlingStep() {
        return stepBuilderFactory.get("crawlingStep")
                .<Place, Optional<CrawlingResult>>chunk(chunkSize)
                .reader(placeReader())
                .processor(instagramBatchProcessor)
                .writer(placeWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Place> placeReader() {
        return new JpaPagingItemReaderBuilder<Place>()
                .name("placeReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT p FROM Place p order by place_id")
                .build();
    }

    @Bean
    public ItemWriter<Optional<CrawlingResult>> placeWriter() {
        // Todo 갱신, 추가 (Instagram, InstagramPost)
        return crawlingResults -> {
            for (Optional<CrawlingResult> crawlingResult : crawlingResults) {
                log.info("Current CrawlingResult={}", crawlingResult.get());
            }
        };
    }
}
