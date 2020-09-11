package com.songpapeople.hashtagmap.job;

import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.scheduler.CrawlingResult;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class InstagramBatchProcessor implements ItemProcessor<Place, Optional<CrawlingResult>> {
    private final InstagramScheduler instagramScheduler;

    @Override
    public Optional<CrawlingResult> process(Place place) {
        return instagramScheduler.update(place);
    }
}
