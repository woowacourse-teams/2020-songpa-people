package com.songpapeople.hashtagmap.instagram.processor;

import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.service.CrawlingResult;
import com.songpapeople.hashtagmap.service.InstagramScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class InstagramBatchProcessor implements ItemProcessor<Place, Optional<CrawlingResult>> {
    private final InstagramScheduleService instagramScheduleService;

    @Override
    public Optional<CrawlingResult> process(Place place) {
        return instagramScheduleService.createCrawlingResult(place);
    }
}
