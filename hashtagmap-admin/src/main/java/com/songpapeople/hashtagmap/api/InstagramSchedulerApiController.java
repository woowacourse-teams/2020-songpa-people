package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.scheduler.InstagramScheduler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instagram-scheduler")
public class InstagramSchedulerApiController {
    private final InstagramScheduler instagramScheduler;

    public InstagramSchedulerApiController(InstagramScheduler instagramScheduler) {
        this.instagramScheduler = instagramScheduler;
    }

    @PutMapping
    public ResponseEntity<Void> update() {
        instagramScheduler.update();
        return ResponseEntity.ok().build();
    }
}
