package com.songpapeople.hashtagmap.config.schedule;

import com.songpapeople.hashtagmap.config.vo.Flag;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Generated
@Profile("data")
@Configuration
@RequiredArgsConstructor
public class DefaultDataConfiguration implements ApplicationRunner {

    private static final int TAG_LEVEL_SIZE = 5;
    private final ScheduleRepository scheduleRepository;
    private final TagLevelRepository tagLevelRepository;

    @Override
    public void run(final ApplicationArguments args) {
        initializeScheduler();
        initializeTagLevel();
    }

    private void initializeScheduler() {
        Schedule schedule = scheduleRepository.findByName("KAKAO")
                .orElseGet(() -> new Schedule("KAKAO", "auto", Flag.N));
        scheduleRepository.save(schedule);
    }

    private void initializeTagLevel() {
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        if (tagLevels.size() != TAG_LEVEL_SIZE) {
            tagLevelRepository.deleteAll();

            for (int i = 0; i < TAG_LEVEL_SIZE; i++) {
                tagLevels.add(new TagLevel());
            }

            tagLevelRepository.saveAll(tagLevels);
        }
    }
}
