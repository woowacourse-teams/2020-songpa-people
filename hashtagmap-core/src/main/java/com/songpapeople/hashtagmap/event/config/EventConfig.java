package com.songpapeople.hashtagmap.event.config;

import com.songpapeople.hashtagmap.event.process.EventBrokerGroup;
import com.songpapeople.hashtagmap.event.process.EventConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventConfig {

    @Bean
    public EventConsumer eventConsumer() {
        return new EventConsumer(eventBrokers());
    }

    @Bean
    public EventBrokerGroup eventBrokers() {
        return new EventBrokerGroup();
    }
}
