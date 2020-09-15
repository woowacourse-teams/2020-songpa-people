package com.songpapeople.hashtagmap.job;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBatchConfig {
    @Bean
    public JobLauncherTestUtils myTestJobLauncher() {
        return new JobLauncherTestUtils();
    }
}
