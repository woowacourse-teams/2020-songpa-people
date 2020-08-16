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
public class ScheduleDataConfiguration implements ApplicationRunner {

    private final ScheduleRepository scheduleRepository;
    private final TagLevelRepository tagLevelRepository;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        Schedule schedule = scheduleRepository.findByName("KAKAO")
                .orElseGet(() -> new Schedule("KAKAO", "auto", Flag.N));
        scheduleRepository.save(schedule);

        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        if (tagLevels.isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                tagLevels.add(new TagLevel());
            }
            tagLevelRepository.saveAll(tagLevels);
        }
    }
}
