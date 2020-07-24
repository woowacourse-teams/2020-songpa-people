package com.songpapeople.hashtagmap;

import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;

import java.util.Arrays;

public class SchedulerTestResource {

    protected static PostDtos createPostDtos() {
        return new PostDtos(Arrays.asList(
                new PostDto("1", "1"),
                new PostDto("2", "2"),
                new PostDto("3", "3"),
                new PostDto("4", "4"),
                new PostDto("5", "5"),
                new PostDto("6", "6"),
                new PostDto("7", "7"),
                new PostDto("8", "8"),
                new PostDto("9", "9")
        ));
    }
}
