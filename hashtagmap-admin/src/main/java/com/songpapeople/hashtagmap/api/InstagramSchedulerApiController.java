package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instagram-scheduler")
public class InstagramSchedulerApiController {
    private final InstagramScheduler instagramScheduler;

    public InstagramSchedulerApiController(InstagramScheduler instagramScheduler) {
        this.instagramScheduler = instagramScheduler;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<Void> update() {
        instagramScheduler.update();
        return CustomResponse.empty();
    }
}
