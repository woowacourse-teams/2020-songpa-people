package com.songpapeople.hashtagmap.event.config;

import com.songpapeople.hashtagmap.event.process.EventBrokerGroup;
import com.songpapeople.hashtagmap.event.process.EventConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventConfiguration {

    @Bean
    public EventConsumer eventConsumer(EventBrokerGroup eventBrokerGroup) {
        return new EventConsumer(eventBrokerGroup);
    }

    @Bean
    public EventBrokerGroup eventBrokers() {
        return new EventBrokerGroup();
    }
}
